package org.northstar.bricks.components;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.dao.UserDao;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class Pager {
    private int page;
    private int maxPerPage;
    private int totalPages;

    public int getTotalPages(){
        return totalPages;
    }
    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isNextExists(){
       return page < totalPages;
       // return !getNext().isEmpty();
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
