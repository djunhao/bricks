package org.northstar.bricks.web.components;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.Show;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.web.auth.CurrentUser;

import java.util.Arrays;
import java.util.List;

@Show("Decorator.html")
@Singleton
public abstract class Decorator {
    private final List<String> PAGES;
    @Inject
    private Provider<CurrentUser> currentUser;
    private boolean logout;

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    protected Decorator() {
        PAGES = Arrays.asList("Home", "Flow", "About");
    }

    public List<String> getPages() {
        return PAGES;
    }

    public boolean isUserExists() {
        return getLoginUser().isAuthenticated();
    }

    public abstract String getPageTitle();

    public CurrentUser getLoginUser() {
        return currentUser.get();
    }

    @Get
    public void logout() {
        if (logout)
            getLoginUser().logout();
    }
}
