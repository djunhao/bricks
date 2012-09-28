package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.routing.Redirect;
import org.northstar.bricks.web.auth.CurrentUser;
import org.northstar.bricks.core.dao.RoleDao;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.Role;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.auth.Secure;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-18
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
@At("/useradmin/create")
public class CreateUser {

    private UserDao userDao;
    private RoleDao roleDao;

    private User user = new User();
    private Role role = new Role();
    private List<Role> roleList;
    @Inject
    private CurrentUser currentUser;
    @Inject
    private Redirect redirect;

    @Inject
    void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Get
    @Secure
    String load() {
        roleList = roleDao.findAll();
        return null;
    }

    @Post
    @Secure
    String create() {
        Role aRole = roleDao.getRoleByName(role.getName());
        System.out.println(">>> Selected role name is: " + aRole.getName());
        user.setRole(aRole);
        userDao.save(user);
        return redirect.to(Home.class);
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
