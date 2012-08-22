package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;
import org.northstar.bricks.services.Identity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class Login extends Decorator {

    @Override
    public String getPageTitle(){
        return "Bricks - 用户登录";
    }
}
