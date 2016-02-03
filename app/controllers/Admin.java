package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by sbt-gorbov-ls on 02.02.2016.
 */
@With(Secure.class)
@Check("admin")
public class Admin extends Controller {
    public static void index() {
        render();
    }
}
