package org.northstar.bricks.web.auth;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.SessionScoped;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-20
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
public class AuthModule extends AbstractModule {
    @Override
    protected void configure() {
        MethodInterceptor interceptor = new SecureMethodInterceptor();
        requestInjection(interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Secure.class), interceptor);
        bindInterceptor(Matchers.annotatedWith(Secure.class), Matchers.any(), interceptor);
        interceptor = new AdminMethodInterceptor();
        requestInjection(interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(AdminOnly.class), interceptor);
        bindInterceptor(Matchers.annotatedWith(AdminOnly.class), Matchers.any(), interceptor);

        bind(CurrentUser.class).in(SessionScoped.class);

    }
}
