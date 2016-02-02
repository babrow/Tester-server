package controllers;

public class Security extends Secure.Security {

    static boolean authentify(String email, String password) {
        return true;//Account.connect(email, password) != null;
    }

    static boolean check(String profile) {
//        if ("admin".equals(profile)) {
//            return Account.find("byEmail", connected()).<Account>first().isAdmin;
//        }
        return false;
    }

    static void onDisconnected() {
        Application.index();
    }

    static void onAuthenticated() {
        //Admin.index();
    }

}

