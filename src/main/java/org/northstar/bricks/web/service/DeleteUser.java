package org.northstar.bricks.web.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;
import org.northstar.bricks.config.URIContext;
import org.northstar.bricks.core.dao.UserDao;
import org.northstar.bricks.core.domain.User;
import org.northstar.bricks.web.auth.Secure;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-25
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class DeleteUser {
    @Inject
    private UserDao userDao;
    @Inject
    private Request request;

    @Get
    @Secure
    public Reply<?> load(@Named("id") Long id) {
        User user = userDao.findById(id);
        userDao.delete(user);
        return Reply.saying().redirect(request.context() + URIContext.ROOT);
    }
}
