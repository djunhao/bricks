package org.northstar.bricks.components;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.Visible;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class UserPager {
    private int page;
    private int maxPerPage;
    private int totalPages;

    //@Inject
    private UserFinder finder;
    //@Inject
    private UserDao dao;

    @Inject
    public UserPager(UserFinder finder, UserDao dao){
        this.finder = finder;
        this.dao = dao;

    }

    private ArrayList<User> getNext(){
        int start = page * maxPerPage;
        return finder.listUsers(start, maxPerPage);
    }

    public int getTotalPages(){
        int usersCount = (int)dao.getUserCounts();
        totalPages = ((usersCount-1)/maxPerPage+1);
        return totalPages;
    }
    private void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }
    public int getPage() {
        return page;
    }
    @Get
    void get(){
        getTotalPages();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isNextExists(){
       //return page < totalPages;
        return !getNext().isEmpty();
    }
    public boolean isPrevExists(){
        return page > 1;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }
    public int getMaxPerPage(){
        return maxPerPage;
    }



}
