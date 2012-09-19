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

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-17
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
@Decorated
@Singleton
public class EditUser extends Decorator {
    private User user;
    private Role role;
    private List<Role> roleList;

    private UserDao userDao;
    private RoleDao roleDao;


    @Get
    String load(@Named("id") Long id) {
        if(!isUserExists()) {
            return "/login";
        } else {
            user = userDao.findById(id);
            roleList = roleDao.findAll();
            return null;
        }
    }
    @Post
    String update(@Named("id") Long id){
        load(id);
        Role saveRold = roleDao.getRoleByName(role.getName());
        user.setRole(saveRold);
        userDao.saveOrUpdate(user);
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

    @Override
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
}
