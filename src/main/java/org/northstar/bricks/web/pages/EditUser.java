package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.sitebricks.Show;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Put;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.config.URIContext;
import org.northstar.bricks.core.dao.RoleDao;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.Role;
import org.northstar.bricks.core.domain.User;
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
@Decorated
@Singleton  //必须是单例模式才能保证user实例不变，以便更新到数据库
@Show("EditUser.fml")
public class EditUser extends Decorator {
    private Role role = new Role();
    private User user = new User();

    private List<Role> roleList;

    private UserDao userDao;
    private RoleDao roleDao;

    @Inject
    private Request request;

    @Inject
    public EditUser(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Get
    @Secure
    String load(@Named("id") Long id) {
        this.user = userDao.findById(id);
        this.roleList = roleDao.findAll();
        return null;
    }

    @Secure
    @Put
    String update(@Named("id") Long id) {
        Role aRole = roleDao.getRoleByName(role.getName());
        user.setRole(aRole);
        userDao.save(user);
        return request.context() + URIContext.ROOT;
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

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
