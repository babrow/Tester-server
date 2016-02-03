package controllers;

import models.Account;
import models.TestResult;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

@With(Secure.class)
public class Application extends Controller {
    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            Account user = Account.find("byEmail", Security.connected()).first();
            renderArgs.put("account", user);
        }
    }

    public static void index() {
        List<TestResult> testResults = TestResult.findAll(); 
    	
    	render(testResults);
    }    
}