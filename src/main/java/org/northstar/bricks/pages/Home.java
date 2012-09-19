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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Home extends Decorator {

    private List<User> pagedUsers;
    private int page;
    private Integer maxPerPage = 5;
    private final UserDao dao;
   /* @Inject
    private RoleDao roleDao;*/
    @Inject
    private Logger logger;

    @Inject
    Home(UserDao dao) {
        this.dao = dao;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getPagedUsers() {
        long startIndex = 0;
        if(page < 1) {
            page = 1;
        }
        startIndex = (page - 1) * maxPerPage - 1;
        return dao.findPagedUsers(startIndex, maxPerPage);
        //return dao.findAll();
    }

    @Override
    public String getPageTitle() {
        return "主页";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxPages(){
        int userCounts = dao.getUserCounts();
        return (userCounts - 1) / maxPerPage + 1;
    }
    /*@Get
    void load(){
        User user =
        Role role = roleDao.getRoleByName("leader");
        logger.info("get role from: " + role.getName());
        Role role = new Role();
        role.setName("leader");
        role.setMode((byte) 0);
        user.setRole(role);
        dao.createNewUser(user);
    }*/
}
