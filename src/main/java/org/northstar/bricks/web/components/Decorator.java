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

    protected Decorator() {
        PAGES = Arrays.asList("Home", "Flow", "About");
    }

    public List<String> getPages() {
        return PAGES;
    }

    public boolean isLoggedIn() {
        return getLoginUser().isAuthenticated();
    }

    public abstract String getPageTitle();

    public CurrentUser getLoginUser() {
        return currentUser.get();
    }
}
