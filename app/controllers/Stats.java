package controllers;

import models.Account;
import models.Test;
import models.TestResultAttr;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Util;
import play.mvc.With;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by babrow on 27.02.2016.
 */
@With(Secure.class)
public class Stats extends Controller {

    @Util
    public static List<String[]> getTappingData(Collection<TestResultAttr> attrs) {
        List<String[]> results = new ArrayList<>();
        for (TestResultAttr attr : attrs) {
            results.add(new String[]{String.valueOf(attr.getTestAttr().getSortOrder() - 1), String.valueOf(attr.getVal())});
        }
        return results;
    }

    public static void index() {
        Test test = Test.findById(1L);
        Account account = Account.find("byEmail", Security.connected()).first();

        TestResultAttr lastRes = TestResultAttr.find("testResult.test = ? and testResult.account = ? order by testResult.resultDate desc", test, account).first();
        List<String[]> last = getTappingData(Arrays.asList(lastRes));
        List<TestResultAttr> allRes = TestResultAttr.find("testResult.test = ? and testResult.account = ?", test, account).fetch();
        List<String[]> all = getTappingData(allRes);
        @SuppressWarnings("JpaQlInspection") Query query = JPA.em().createQuery("select t.sortOrder - 1, avg(a.val) from TestResult r, TestResultAttr a, TestAttr t where a.testResult = r and a.testAttr = t and r.test = :test and r.account = :account group by t.sortOrder");
        query.setParameter("test", test);
        query.setParameter("account", account);
        List<String[]> avg = query.getResultList();


        render(account, last, avg, all);
    }
}
