package org.northstar.bricks.web.pages;

import com.google.inject.Inject;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.northstar.bricks.data.dao.UserDao;
import org.northstar.bricks.data.domain.User;
import org.northstar.bricks.web.auth.Secure;
import org.northstar.bricks.web.components.Decorator;

import java.util.List;
import java.util.logging.Logger;

@Decorated
public class Flow extends Decorator {

    private List<User> pagedUsers;
    private int page;
    private Integer maxPerPage = 5;
    private final UserDao dao;

    @Inject
    private Logger logger;

    @Inject
    Flow(UserDao dao) {
        this.dao = dao;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Get
    @Secure
    public Object load() {
        long startIndex = 0;
        if (page < 1) {
            page = 1;
        }
        startIndex = (page - 1) * maxPerPage - 1;
        pagedUsers = dao.findUsers(startIndex, maxPerPage);
        return null;
    }

    public List<User> getPagedUsers() {
        return pagedUsers;
    }

    public String getPageTitle() {
        return "测试新Html Layout";
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public int getMaxResults() {
        return dao.getUserCounts();
    }

}
