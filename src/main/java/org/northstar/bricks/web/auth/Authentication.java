package org.northstar.bricks.web.auth;

import org.northstar.bricks.data.dao.UserDao;
import org.northstar.bricks.data.domain.User;

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
    private final Identity identity;
    private final UserDao dao;

    @Inject
    public Authentication(UserDao dao, Identity identity) {
        this.dao = dao;
        this.identity = identity;
    }

    public Identity authenticate(String login, String password) {
        List<User> userSet = dao.authenticated(login, password);
        for (User u : userSet) {
            identity.setUser(u);
        }
        return identity;
    }
}
