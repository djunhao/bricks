package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-27
 * Time: 上午8:44
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
public class UserSession implements Provider<User> {
    private final Provider<UserDao> userDaoProvider;
    private volatile User user;

    @Inject
    public UserSession(Provider<UserDao> userDaoProvider) {
        this.userDaoProvider = userDaoProvider;
    }

    public void login(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    @Override
    public User get() {
        return user;
    }

    public void logout() {
        this.user = null;
    }
}
