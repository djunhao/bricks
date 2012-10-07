package org.northstar.bricks.web.pages;

import com.google.inject.Singleton;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.auth.Secure;
import org.northstar.bricks.web.components.Decorator;

@Singleton
@Decorated
public class Flow extends Decorator {

    @Get
    @Secure
    public String get() {
        return null;
    }

    public String getName() {
        return getLoginUser().getName();
    }

    public String getPageTitle() {
        return "需密码访问页面";
    }

}
