package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.auth.CurrentUser;
import org.northstar.bricks.web.auth.Secure;
import org.northstar.bricks.web.components.Decorator;

@Decorated
public class Flow extends Decorator {

    @Inject
    private CurrentUser currentUser;

    @Get
    @Secure
    public String get() {
        return null;
    }

    public String getName() {
        return getCurrentUser().getName();
    }

    public String getPageTitle() {
        return "需密码访问页面";
    }

    public User getCurrentUser() {
        return currentUser.getUser();
    }

    public boolean isLoggedIn() {
        return currentUser.isAuthenticated();
    }
}
