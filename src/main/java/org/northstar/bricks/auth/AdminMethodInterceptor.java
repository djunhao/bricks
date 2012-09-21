package org.northstar.bricks.auth;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.headless.Reply;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-20
 * Time: 下午11:07
 * To change this template use File | Settings | File Templates.
 */
public class AdminMethodInterceptor implements MethodInterceptor {
    private static final ImmutableSet<String> ADMINS = ImmutableSet.of("northstar", "djh",
            "djunhao");
    @Inject
    private Provider<CurrentUser> currentUser;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        CurrentUser user = currentUser.get();
        if (!user.isAuthenticated() || !ADMINS.contains(user.getUser().getName())) {
            Reply.saying().redirect("/login");
        }
        return invocation.proceed();
    }
}
