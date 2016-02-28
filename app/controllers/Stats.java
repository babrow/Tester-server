package controllers;

import models.Account;
import models.Test;
import models.TestResult;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created by babrow on 27.02.2016.
 */
@With(Secure.class)
public class Stats extends Controller {

    public static void index() {
        Test test = Test.findById(1L);
        Account account = Account.find("byEmail", Security.connected()).first();
        List<TestResult> testResults = TestResult.find("byTestAndAccountAndResultIsNotNull", test, account).fetch();

        render(account, testResults);
    }
}
