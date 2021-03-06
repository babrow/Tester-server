package controllers;

import com.google.gson.Gson;
import models.*;
import play.Logger;
import play.mvc.Controller;
import utils.Utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Api extends Controller {
    public static final int ERR_CODE = 999;
    public static final String SUCCESS_RESPONSE = "Данные удачно сохранены";
    public static final String RESULTS_WRONG = "Результаты теста не достоверны";


    public static void saveResult() {
        try {
            Map<String, String> data = null;

            String str = params.get("body");
            data = new Gson().fromJson(str, HashMap.class);

            Test test = Test.findById(Long.valueOf(data.get("testId")));
            Account account = Account.findById(Long.valueOf(data.get("accountId")));

            BigDecimal result = Utils.parseNumber(data.get("result"), BigDecimal.class);
            String resultDescr = data.get("resultDescr");
            TestResult tr;
            if (RESULTS_WRONG.equals(resultDescr)) {
                tr = new TestResult(account, test, new Date()).save();
            } else {
                tr = new TestResult(account, test, new Date(), result, resultDescr).save();
            }

            List<TestAttr> testAttrs = TestAttr.find("byTest", test).fetch();
            for (TestAttr testAttr : testAttrs) {
                BigDecimal value = Utils.parseNumber(data.get(testAttr.getName()), BigDecimal.class);
                if (value != null) {
                    new TestResultAttr(testAttr, tr, value).save();
                }
            }
        } catch (Exception e) {
            Logger.error(e, "Error saving results");
            error(ERR_CODE, "Ошибка сервера при попытке записи результата теста. Повторите попытку позднее.");
        }
        renderText(SUCCESS_RESPONSE);
    }

    public static void login() {
        Account account = null;
        try {
            String str = params.get("body");
            Account acc = new Gson().fromJson(str, Account.class);
            String email = acc.getEmail();
            String password = acc.getPassword();
            account = Account.find("byEmail", acc.getEmail()).first();
            if (account == null) {
                account = new Account(email, password);
                account.save();
            } else if (!account.getPassword().equals(password)) {
                account = null;
            }
        } catch (Exception e) {
            Logger.error(e, "Error getting user");
            error(ERR_CODE, "Ошибка сервера при попытке авторизации. Повторите попытку позднее.");
        }
        renderJSON(account);
    }
}
