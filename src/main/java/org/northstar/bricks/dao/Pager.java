package org.northstar.bricks.dao;

import com.google.inject.Inject;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class Pager {
    private int page;
    private int maxPerPage;

    public Pager(){

    }
    public abstract ArrayList<?> getNext();
    public abstract ArrayList<?> getPrevious();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isNextExists(){
        return !getNext().isEmpty();
    }
    public boolean isPrevExists(){
        if(page<2)
            return false;
        return true;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }
    public int getMaxPerPage(){
        return maxPerPage;
    }
}