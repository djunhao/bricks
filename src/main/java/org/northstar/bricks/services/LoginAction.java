package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.RequestScoped;
import com.google.sitebricks.Show;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;
import org.northstar.bricks.pages.Flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private Identity identity;
   @Inject
   private UserFinder finder;
    @Post
    public Reply login()
    {
        System.out.println("loginAction excuted ...");
        Set<User> userSet = finder.authenticated(name, password);
        Map<String, String> result = new HashMap<String, String>();
        if(userSet.isEmpty()){
            result.put("error", "用户名或密码错误！");
            return Reply.with(result).as(Json.class);
        }
        for (User user: userSet){
            identity.setName(user.getName());
            identity.setUid(user.getId());
        }
        return Reply.saying().redirect("flow");
    }
}
