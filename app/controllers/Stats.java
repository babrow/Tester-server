package controllers;

import models.Account;
import models.Test;
import models.TestResult;
import models.TestResultAttr;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Util;
import play.mvc.With;

import javax.persistence.Query;
import java.util.ArrayList;
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
            results.add(new String[]{attr.getTestAttr().getName(), String.valueOf(attr.getVal())});
        }
        return results;
    }

    public static void index() {
        Test test = Test.findById(1L);
        Account account = Account.find("byEmail", Security.connected()).first();
        List<TestResult> testResults = TestResult.find("byTestAndAccountAndResultIsNotNull", test, account).fetch();

        TestResultAttr firstRes = TestResultAttr.find("testResult.test = ? and testResult.account = ? order by testResult.resultDate", test, account).first();
        List<TestResultAttr> allRes = TestResultAttr.find("testResult.test = ? and testResult.account = ?", test, account).fetch();
        @SuppressWarnings("JpaQlInspection") Query query = JPA.em().createQuery("select avg(a.val), t.name from TestResult r, TestResultAttr a, TestAttr t where a.testResult = r and a.testAttr = t and r.test = :test and r.account = :account group by t.name");
        query.setParameter("test", test);
        query.setParameter("account", account);
        List resultList = query.getResultList();


        render(account, testResults);
    }
}
