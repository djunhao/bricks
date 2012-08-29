package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: jenny
 * Date: 12-8-27
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class Logout {
    private final User user;

    @Inject
    protected Logout(final User user) {
        this.user = user;
    }

    @Get
    public Reply logout() {
        user.logout();
        return Reply.saying().redirect("/bricks");
    }
}
