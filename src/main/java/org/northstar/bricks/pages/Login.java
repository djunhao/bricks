package org.northstar.bricks.pages;

import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Login extends Decorator {
    public Login() {
    }

    @Override
    public String getPageTitle(){
        return "Bricks - 用户登录";
    }
}
