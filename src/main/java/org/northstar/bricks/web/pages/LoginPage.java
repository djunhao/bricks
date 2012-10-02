package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.Show;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.web.auth.CurrentUser;
import org.northstar.bricks.web.components.Decorator;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Singleton
@Decorated
@Show("Login.html")
public class LoginPage extends Decorator {

    public String getPageTitle() {
        return "Bricks - 用户登录";
    }
}
