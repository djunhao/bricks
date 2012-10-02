package org.northstar.bricks.test;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.Show;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.core.dao.RoleDao;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.Role;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.components.Decorator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-10-2
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
@Decorated
@Singleton
@Show("Test.fml")
public class Test extends Decorator {
    private String message = "hello mvel template";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user = new User();
    private Role role = new Role();

    @Inject
    private RoleDao roleDao;
    @Inject
    private UserDao userDao;

    public String getMessage() {
        return message;
    }
    @Get
    public String load() {
        user = userDao.findById(new Long(5));

        return null;
    }
    public List<Role> getRoles() {
        return roleDao.findAll();
    }
    @Override
    public String getPageTitle() {
        return "Testing Page for mvel template";
    }
}
