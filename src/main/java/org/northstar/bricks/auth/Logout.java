package org.northstar.bricks.auth;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: jenny
 * Date: 12-8-27
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class Logout {
    private final CurrentUser currentUser;

    @Inject
    protected Logout(final CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Get
    public Reply logout() {
        currentUser.logout();
        return Reply.saying().redirect("/bricks");
    }
}
