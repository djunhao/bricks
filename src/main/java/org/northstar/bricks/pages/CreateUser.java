package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.RoleDao;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.Role;
import org.northstar.bricks.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
public class CreateUser {

    private UserDao userDao;
    private RoleDao roleDao;

    private User user = new User();
    private Role role = new Role();
    private List<Role> roleList;
    @Inject
    private CurrentUser currentUser;

    @Inject
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Get
    void load() {
        roleList = roleDao.findAll();
    }

    @Post
    String create() {
        Role aRole = roleDao.getRoleByName(role.getName());
        System.out.println(">>> Selected role name is: " + aRole.getName());
        user.setRole(aRole);
        userDao.save(user);
        return "/";
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

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public String getPageTitle() {
        return "创建用户";

    }

    boolean isUserExists() {
        return currentUser.isAuthenticated();
    }
}
