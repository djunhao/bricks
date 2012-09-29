package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.routing.Redirect;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.northstar.bricks.config.URIContext;
import org.northstar.bricks.web.pages.Login;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-20
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
class SecureMethodInterceptor implements MethodInterceptor {
    @Inject
    private Provider<CurrentUser> currentUserProvider;
    @Inject
    private Redirect redirect;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!currentUserProvider.get().isAuthenticated()) {
            if (Reply.class.isAssignableFrom(invocation.getMethod().getReturnType())) {
                return Reply.saying().redirect(URIContext.LOGIN_PAGE);
            }
            return URIContext.LOGIN_PAGE;
        }
        return invocation.proceed();
    }
}
