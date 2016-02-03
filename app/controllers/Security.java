package controllers;

import models.Account;
import play.Logger;

public class Security extends Secure.Security {

    static boolean authentify(String email, String password) {
        Account account = Account.find("byEmailAndPassword", email, password).first();
        return account != null;
    }

    static boolean check(String profile) {
        if ("admin".equals(profile)) {
            return Account.find("byEmail", connected()).<Account>first().isAdmin();
        }
        return false;
    }

    public static void logout() {
        try {
            Secure.logout();
        } catch (Throwable t) {
            Logger.error(t, "Error logout");
        }
    }

    static void onAuthenticated() {
        Application.index();
    }

}

