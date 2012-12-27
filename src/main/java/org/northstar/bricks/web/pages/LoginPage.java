package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import org.northstar.bricks.web.auth.Authentication;
import org.northstar.bricks.web.auth.Identity;
import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Show("Login.html")
public class LoginPage {
    private String target;
    private String name;
    private String password;
    private Identity identity;
    private Authentication authentication;
    private Logger logger;

    @Inject
    LoginPage(Identity identity, Authentication authentication) {
        this.identity = identity;
        this.authentication = authentication;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(@Named("target") String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Post
    public Object login() {
        identity = authentication.authenticate(name, password);
        Map<String, String> result = new HashMap<String, String>();
        if (identity.isAuthenticated()) {
            logger.info("Login success!");
            result.put("info", "success");
        } else {
            logger.warn("Login failed!");
            result.put("info", "failed");
        }
        return Reply.with(result).as(Json.class);
    }

    @Get
    public Object get() {
        if (identity.isAuthenticated()) {
            return Home.class;
        }
        return null;
    }
   /* @Get
    @At("/:target")
    public Object load(@Named("target") String target) {
            logger.info("target uri is: " + target);
            if (identity.isAuthenticated()) {
                return target;
            }
            return null;
    }*/

    public String getPageTitle() {
        return "用户登录";
    }
}
