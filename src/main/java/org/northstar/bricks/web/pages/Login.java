package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.At;
import org.northstar.bricks.web.auth.CurrentUser;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class Login {

    @Inject
    private CurrentUser currentUser;

    public String getPageTitle() {
        return "Bricks - 用户登录";
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public boolean isUserExists() {
        return currentUser.isAuthenticated();
    }
}
