package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.RequestScoped;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
@RequestScoped
public class LoginAction {
    Injector injector;
    @Inject
    public LoginAction(Injector injector){
        this.injector = injector;
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp)
    {
        Identity identity = injector.getInstance(Identity.class);
        Authentication auth = new Authentication();
        Identity authenticated = auth.authenticate("login","password");
        identity.setUid(authenticated.getUid());
        identity.setName(authenticated.getName());
    }
}
