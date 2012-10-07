package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.config.URIContext;

/**
 * Created with IntelliJ IDEA.
 * User: jenny
 * Date: 12-8-27
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class Logout {
    private final Identity identity;

    @Inject
    protected Logout(final Identity identity) {
        this.identity = identity;
    }

    @Get
    public Reply<Object> logout() {
        identity.logout();
        return Reply.saying().redirect(URIContext.ROOT);
    }
}
