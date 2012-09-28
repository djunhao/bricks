package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.At;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.User;

import java.util.List;
import java.util.logging.Logger;

@At("/")
public class Home {

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
        if (page < 1) {
            page = 1;
        }
        startIndex = (page - 1) * maxPerPage - 1;
        return dao.findPagedUsers(startIndex, maxPerPage);
        //return dao.findAll();
    }

    public String getPageTitle() {
        return "主页";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxPages() {
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
