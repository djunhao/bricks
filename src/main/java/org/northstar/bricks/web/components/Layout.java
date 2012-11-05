package org.northstar.bricks.web.components;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.Show;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.auth.Identity;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-10-16
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */
@Show("Layout1.html")
public abstract class Layout {
    @Inject
    private Provider<Identity> identityProvider;

    public boolean isLoggedIn() {
        return identityProvider.get().isAuthenticated();
    }

    public abstract String getPageTitle();

    public User getLoginUser() {
        return identityProvider.get().getUser();
    }
}
