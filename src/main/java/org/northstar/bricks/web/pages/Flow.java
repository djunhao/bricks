package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.web.auth.CurrentUser;
import org.northstar.bricks.web.auth.Secure;

public class Flow {

    @Inject
    private CurrentUser currentUser;

    @Get
    @Secure
    public void get() {
    }

    @Secure
    public String getName() {
        return getCurrentUser().getUser().getName();
    }

    public String getPageTitle() {
        return "需密码访问页面";
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    boolean isUserExists() {
        return currentUser.isAuthenticated();
    }
}
