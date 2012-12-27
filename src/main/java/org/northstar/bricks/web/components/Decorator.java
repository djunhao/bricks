package org.northstar.bricks.web.components;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.Show;
import org.northstar.bricks.data.domain.User;
import org.northstar.bricks.web.auth.Identity;

import java.util.Arrays;
import java.util.List;

@Singleton
@Show("Layout.html")
public abstract class Decorator {
    private final List<String> PAGES;
    @Inject
    private Provider<Identity> identityProvider;

    protected Decorator() {
        PAGES = Arrays.asList("Home", "Flow", "About");
    }

    public List<String> getPages() {
        return PAGES;
    }

    public boolean isLoggedIn() {
        return identityProvider.get().isAuthenticated();
    }

    public abstract String getPageTitle();

    public User getLoginUser() {
        return identityProvider.get().getUser();
    }
}
