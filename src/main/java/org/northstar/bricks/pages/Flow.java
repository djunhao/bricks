package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.domain.User;

@Decorated
public class Flow extends Decorator {

    private final User identity;

    @Inject
    public Flow(User identity) {
        this.identity = identity;
    }

    public String getName() {
        return identity.getName();
    }

    @Get
    public String get() {
        if (!identity.isAuthenticate())
            return "login";
        return null;
    }

    @Override
    public String getPageTitle() {
        return "Persist value页面";
    }
}
