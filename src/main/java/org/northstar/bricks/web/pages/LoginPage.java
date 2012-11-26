package org.northstar.bricks.web.pages;

import com.google.inject.Singleton;
import com.google.sitebricks.Show;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Show("Login.html")
public class LoginPage {

    public String getPageTitle() {
        return "用户登录";
    }
}
