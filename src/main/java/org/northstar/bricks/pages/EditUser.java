package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.auth.CurrentUser;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.RoleDao;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.Role;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-17
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class EditUser {
    private Role role = new Role();
    private User user = new User();
    private List<Role> roleList = new ArrayList<>();

    private String name;

    private UserDao userDao;
    private RoleDao roleDao;


    @Inject
    private CurrentUser currentUser;

    public EditUser() {
        super();

    }

    @Get
    String load() {
        if(!isUserExists()) {
            return "/login";
        } else {
            user = userDao.findByName(name);
            roleList = roleDao.findAll();
            return null;
        }
    }
    @Post
    String update(){
        Role aRole = roleDao.getRoleByName(role.getName());
        user.setRole(aRole);
        userDao.save(user);
        return "/";
    }

    public List<Role> getRoleList(){
        return roleList;
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

    public String getPageTitle() {
        return "编辑用户信息";
    }
    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    @Inject
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    public boolean isUserExists() {
        return currentUser.isAuthenticated();
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
