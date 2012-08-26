package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction {
    private String name;
    private String password;

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

    @Inject
    private User user;
    @Inject
    private Authentication auth;

    @Post
    public Reply login() {
        System.out.println("loginAction excuted ...");
        user = auth.authenticate(name, password);
        Map<String, String> result = new HashMap<String, String>();
        if(user.isAuthenticate()) {
            result.put("info", "success");

        } else {
            result.put("info", "您输入的用户名或密码不正确！");
        }
        return Reply.with(result).as(Json.class);
    }
}
