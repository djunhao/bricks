package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
import org.northstar.bricks.web.uri.URIContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-17
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
@At(URIContext.USER_EDIT)
public class EditUser {
    private Role role = new Role();
    private User user = new User();
    private List<Role> roleList;

    private UserDao userDao;
    private RoleDao roleDao;

    @Inject
    private Redirect redirect;
    @Inject
    private CurrentUser currentUser;

    @Get
    @Secure
    String load(@Named("id") Long id) {
        user = userDao.findById(id);
        roleList = roleDao.findAll();
      return null;
    }

    @Post
    String update(@Named("id") Long id) {
        Role aRole = roleDao.getRoleByName(role.getName());
        user.setRole(aRole);
        userDao.save(user);
        return URIContext.HOME;
    }

    public List<Role> getRoleList() {
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

    public boolean isLoggedIn() {
        return currentUser.isAuthenticated();
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }
}
