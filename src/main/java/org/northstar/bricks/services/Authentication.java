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
    @Inject
    private UserFinder finder;
    @Inject
    private Identity identity;
    public Identity authenticate(String login, String password)
    {
        Set<User> userSet = finder.authenticated(login, password);
        for(User user: userSet){
            identity.setUid(user.getId());
            identity.setName(user.getName());
        }
        return identity;
    }
}
