package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;

@Decorated
public class Home extends Decorator {

    private ArrayList<User> pagedUsers;
    private int page;
    private int maxPerPage = 4;

    @Inject
    private UserFinder finder;
    @Inject
    private UserDao dao;

    public Home() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
             this.page = page;
    }

    public ArrayList<User> getPagedUsers() {
        if(page<1)
            page = 1;
        int start = (page - 1) * maxPerPage;
        return finder.listUsers(start, maxPerPage);
    }

    @Override
    public String getPageTitle() {
        return "主页再次测试！";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }



}
