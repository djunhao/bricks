package org.northstar.bricks.pages;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.components.Decorator;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;
import java.util.List;

@Decorated
public class Home extends Decorator {

    private ArrayList<User> pagedUsers;
    private int page;
    private int maxPerPage = 4;

    @Inject
    private UserFinder finder;
    @Inject
    private UserDao dao;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getPagedUsers() {
        if (page < 1)
            page = 1;
        int start = (page - 1) * maxPerPage;
        return finder.listUsers(start, maxPerPage);
    }

    public int getTotalPages() {
        int availableRows = dao.getUserCounts();
        int totalPages = (availableRows - 1) / maxPerPage + 1;
        return totalPages;
    }

    @Override
    public String getPageTitle() {
        return "主页再次测试！";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

}
