package controllers;

import com.google.gson.Gson;
import models.*;
import play.Logger;
import play.mvc.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Api extends Controller {

    public static void saveResult() {
        try {
            Map<String, String> params = null;

            String str = params.get("body");
            params = new Gson().fromJson(str, HashMap.class);

            Test test = Test.findById(params.get("testId"));
            Account account = Account.findById(params.get("accountId"));

            TestResult tr = new TestResult(account, test, new Date()).save();

            List<TestAttr> testAttrs = TestAttr.find("byTest", test).fetch();
            for (TestAttr testAttr : testAttrs) {
                BigDecimal value = new BigDecimal(params.get(testAttr.getName()));
                if (value != null) {
                    new TestResultAttr(testAttr, tr, value).save();
                }
            }
        } catch (Exception e) {
            Logger.error(e, "Error saving results");
            error(9999,"asdasdas");
        }
        ok();
    }

    public static void login() {
        Account account = null;
        try {
            String str = params.get("body");
            Account acc = new Gson().fromJson(str, Account.class);
            account = Account.find("byEmail", acc.getEmail()).first();
        } catch (Exception e) {
            Logger.error(e, "Error getting user");
            error(e);
        }
        renderJSON(account);
    }
}
