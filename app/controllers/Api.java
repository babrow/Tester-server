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
    public static final int ERR_CODE = 999;

    public static void saveResult() {
        try {
            Map<String, String> data = null;

            String str = params.get("body");
            data = new Gson().fromJson(str, HashMap.class);

            Test test = Test.findById(Long.valueOf(data.get("testId")));
            Account account = Account.findById(Long.valueOf(data.get("accountId")));

            TestResult tr = new TestResult(account, test, new Date()).save();

            List<TestAttr> testAttrs = TestAttr.find("byTest", test).fetch();
            for (TestAttr testAttr : testAttrs) {
                String value = data.get(testAttr.getName());
                if (value != null) {
                    new TestResultAttr(testAttr, tr, new BigDecimal(value)).save();
                }
            }
        } catch (Exception e) {
            Logger.error(e, "Error saving results");
            error(ERR_CODE, "Ошибка сервера при попытке записи результата теста. Повторите попытку позднее.");
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
            error(ERR_CODE, "Ошибка сервера при попытке авторизации. Повторите попытку позднее.");
        }
        renderJSON(account);
    }
}
