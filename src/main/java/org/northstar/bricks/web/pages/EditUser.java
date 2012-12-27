package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Put;
import com.google.sitebricks.rendering.Decorated;
import com.google.sitebricks.routing.Redirect;
import org.northstar.bricks.data.dao.RoleDao;
import org.northstar.bricks.data.dao.UserDao;
import org.northstar.bricks.data.domain.Role;
import org.northstar.bricks.data.domain.User;
import org.northstar.bricks.web.auth.Secure;
import org.northstar.bricks.web.components.Decorator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-17
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
//@Singleton
@Decorated
public class EditUser extends Decorator {
    private Role role = new Role();
    private User user = new User();

    private List<Role> roleList;

    private UserDao userDao;
    private RoleDao roleDao;

    @Inject
    private Request request;
    @Inject
    private Redirect redirect;

    @Inject
    public EditUser(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Get
    @Secure
    Object load(@Named("id") Long id) {
        this.user = userDao.findById(id);
        this.roleList = roleDao.findAll();
        return null;
    }

    @Secure
    @Put
    Object update(@Named("id") Long id) {
        Role aRole = roleDao.getRoleByName(role.getName());
        user.setRole(aRole);
        userDao.save(user);
        return Home.class;
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
        return "更新成员信息";
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
