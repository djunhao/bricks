package org.northstar.bricks.components;

import com.google.inject.Inject;
import com.google.sitebricks.Show;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.domain.User;

import java.util.Arrays;
import java.util.List;

@Show("Decorator.html")
public abstract class Decorator {
    private final List<String> PAGES;
    @Inject
    private CurrentUser currentUser;
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
        return currentUser.isAuthenticated();
    }

    public abstract String getPageTitle();

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Get
    public void logout() {
        if (logout)
            currentUser.logout();
    }
}
