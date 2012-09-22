package org.northstar.bricks.auth;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.SessionScoped;
import org.aopalliance.intercept.MethodInterceptor;

import static com.google.inject.matcher.Matchers.any;

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
        bindInterceptor(any(), Matchers.annotatedWith(Secure.class), interceptor);

        interceptor = new AdminMethodInterceptor();
        requestInjection(interceptor);
        bindInterceptor(any(), Matchers.annotatedWith(AdminOnly.class), interceptor);

        bind(CurrentUser.class).in(SessionScoped.class);
        /*install(new ServletModule() {
            @Override
            protected void configureServlets() {
                filter("*//*").through(AuthFilter.class);
            }

        });*/
    }
}
