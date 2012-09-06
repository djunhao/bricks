package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.domain.User;

import java.util.List;

@Decorated
public class Home extends Decorator {

    private List<User> pagedUsers;
    private int page;
    private int maxPerPage = 4;
    private final UserDao dao;

    @Inject
    Home(UserDao dao){
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

}
