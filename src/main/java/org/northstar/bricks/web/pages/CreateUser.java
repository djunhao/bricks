package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.core.dao.RoleDao;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.Role;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.auth.Secure;
import org.northstar.bricks.web.components.Decorator;
import org.slf4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
@Decorated
@Singleton
public class CreateUser extends Decorator {

    private UserDao userDao;
    private RoleDao roleDao;

    private User user = new User();
    private Role role = new Role();
    private List<Role> roleList;

    //@Inject
    private Logger logger;
    @Inject
    private Request request;

    @Inject
    CreateUser(UserDao userDao, UserDao userDao1, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Get
    @Secure
    Object load() {
        roleList = roleDao.findAll();
        return null;
    }

    @Post
    @Secure
    Object create() {
        Role aRole = roleDao.getRoleByName(role.getName());
        logger.info(">>> Selected role name is: " + aRole.getName());
        user.setRole(aRole);
        userDao.save(user);
        return Home.class;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String getPageTitle() {
        return "创建用户";

    }

}
