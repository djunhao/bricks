package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import org.northstar.bricks.data.dao.UserDao;
import org.northstar.bricks.data.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-17
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
public class Identity {
    public static final String SESSION_COOKIE_NAME = "x-bricks-session-id";

    @Inject
    private HttpServletRequest request;
    @Inject
    private UserDao userDao;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String newSessionId() {
        return UUID.randomUUID().toString();
    }

    public boolean isAuthenticated() {
        return this.user != null;
    }

    public void logout() {
        this.user = null;
    }

    public Cookie getSessionCookie() {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (SESSION_COOKIE_NAME.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

}
