package org.northstar.bricks.components;

import com.google.inject.Inject;
import org.northstar.bricks.dao.Pager;
import org.northstar.bricks.dao.UserFinder;
import org.northstar.bricks.domain.User;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-15
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class TestPager extends Pager {
    private int maxPerPage;
    private int page;

    public TestPager(){
        maxPerPage = super.getMaxPerPage();
        page = super.getPage();
    }

    @Inject
    private UserFinder finder;

    @Override
    public ArrayList<User> getNext(){

        int start = page * maxPerPage;
        return finder.listUsers(start, maxPerPage);
    }
    @Override
    public ArrayList<User> getPrevious(){

        int start = (page-2) * maxPerPage;
        return finder.listUsers(start, maxPerPage);
    }
}
