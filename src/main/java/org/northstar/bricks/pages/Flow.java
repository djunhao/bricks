package org.northstar.bricks.pages;

import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;

@Decorated
public class Flow extends Decorator {

    @Get
    public String get() {
        if (isUserExists())
            return null;
        return "login";
    }

    public String getName() {
        return getCurrentUser().getUser().getName();
    }

    @Override
    public String getPageTitle() {
        return "需密码访问页面";
    }

}
