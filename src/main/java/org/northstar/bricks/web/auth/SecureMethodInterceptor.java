package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.routing.Redirect;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.northstar.bricks.config.URIContext;
import org.northstar.bricks.web.pages.Home;
import org.northstar.bricks.web.pages.LoginPage;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-20
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
class SecureMethodInterceptor implements MethodInterceptor {
    @Inject
    private Provider<Identity> identityProvider;
    @Inject
    private Provider<Redirect> redirectProvider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Identity identity = identityProvider.get();
        //String contextPath = redirectProvider.get().context();
        if (!identity.isAuthenticated()) {
            if (Reply.class.isAssignableFrom(invocation.getMethod().getReturnType())) {
                return Reply.saying().redirect(redirectProvider.get().to(LoginPage.class));
            }
            return LoginPage.class;
        }
        return invocation.proceed();
    }
}
