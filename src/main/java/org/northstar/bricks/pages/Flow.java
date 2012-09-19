package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.components.Decorator;

public class Flow {

    @Inject
    private CurrentUser currentUser;

    @Get
    public String get() {
        if (isUserExists())
            return null;
        return "login";
    }

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
