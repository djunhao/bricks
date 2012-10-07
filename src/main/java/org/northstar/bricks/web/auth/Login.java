package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class Login {

    private String name;
    private String password;

    private Identity identity;
    private Authentication auth;

    @Inject
    public Login(Identity identity, Authentication auth) {
        this.identity = identity;
        this.auth = auth;
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
    public Reply login() {
        System.out.println("loginAction excuted ...");
        identity = auth.authenticate(name, password);
        Map<String, String> result = new HashMap<String, String>();
        if (identity.isAuthenticated()) {
            result.put("info", "success");
        } else {
            result.put("info", "您输入的用户名或密码不正确！");
        }
        return Reply.with(result).as(Json.class);

    }
}
