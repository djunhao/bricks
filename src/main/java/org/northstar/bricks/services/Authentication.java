package org.northstar.bricks.services;

import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Authentication {
    private final UserFinder finder;
    private final User user;

    @Inject
    public Authentication(UserFinder finder, User user) {
        this.finder = finder;
        this.user = user;
    }

    public User authenticate(String login, String password) {
        Set<User> userSet = finder.authenticated(login, password);
        for (User u : userSet) {
            user.setId(u.getId());
            user.setName(u.getName());
        }
        return user;
    }
}
