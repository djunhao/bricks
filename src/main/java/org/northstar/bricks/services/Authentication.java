package org.northstar.bricks.services;

import org.northstar.bricks.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class Authentication {
    public Identity authenticate(String login, String password)
    {
        User user = new User();
        Identity identity = new Identity();
        identity.setUid(user.getId());
        return identity;
    }
}
