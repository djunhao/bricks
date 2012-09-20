package org.northstar.bricks.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
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
    private Provider<CurrentUser> currentUser;
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (currentUser.get().isAuthenticated()) {
            throw new IllegalAccessException("Anonymous users may not access this function");
        }
        return invocation.proceed();
    }
}
