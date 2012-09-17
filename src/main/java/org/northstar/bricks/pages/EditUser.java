package org.northstar.bricks.pages;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.User;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-16
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
@Decorated
public class EditUser extends Decorator {
    @Inject
    private UserDao dao;
    @Inject
    private Logger logger;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    @Get
    void load(@Named("name") String name){
        //String tmp = tmp.asString(name, Charsets.UTF_8);
        //user = dao.findByName(name);
        user = dao.findByName(name);
        logger.info("user id is: " + user.getRid());
    }

    @Override
    public String getPageTitle() {
        return "Editing User";
    }
}
