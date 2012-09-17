package org.northstar.bricks.services;

import com.google.inject.name.Named;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Authentication {
    private final CurrentUser currentUser;
    private final UserDao dao;

    @Inject
    public Authentication(UserDao dao, CurrentUser currentUser) {
        this.dao = dao;
        this.currentUser = currentUser;
    }

    public CurrentUser authenticate(String login, String password) {
        List<User> userSet = dao.authenticated(login, password);
        for (User u : userSet) {
            currentUser.setUser(u);
        }
        return currentUser;
    }
}
