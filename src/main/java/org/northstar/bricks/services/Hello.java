package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Hello {
    @Inject
    private UserFinder finder;

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

    @Post
    public Reply<Map<String, String>> hello() {
       ArrayList<User> users = finder.authenticated(name, password);
        System.out.println("\n>>>[BRICKS] Hello world!");
        Map<String, String> result = new HashMap<String, String>();
        if (!users.isEmpty())
            result.put("info", "用户" + name + "登录成功！");
        else result.put("info", "用户名或密码错误！");
        return Reply.with(result).as(Json.class);
    }
}
