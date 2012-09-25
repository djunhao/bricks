package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.headless.Reply;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

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

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        CurrentUser currentUser = currentUserProvider.get();
        if (currentUser.isAuthenticated()) {
            Reply.saying().redirect("/login");
        }
        return invocation.proceed();
    }
}
