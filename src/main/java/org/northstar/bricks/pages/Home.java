package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.RoleDao;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.Role;
import org.northstar.bricks.domain.User;

import java.util.List;

@Decorated
public class Home extends Decorator {

    private List<User> pagedUsers;
    private int page;
    private Integer maxPerPage = 5;
    private final UserDao dao;
    @Inject
    private RoleDao roleDao;

    @Inject
    Home(@Named("orientdb") UserDao dao) {
        this.dao = dao;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getPagedUsers() {
        return dao.findPagedUsers();
    }

    @Override
    public String getPageTitle() {
        return "主页再次测试！";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxPages(){
        int userCounts = dao.getUserCounts();
        return (userCounts - 1) / maxPerPage + 1;
    }
/*
    @Get
    void load(){
        User user = new User();
        user.setName("ljx");
        user.setPassword("112233");
        Role role = roleDao.getRoleByType("leader");
        user.setRole(role);
        dao.createNewUser(user);
    }*/
}
