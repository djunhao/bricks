package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.headless.Reply;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-21
 * Time: 上午12:13
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class AuthFilter implements Filter {
    @Inject
    private Provider<CurrentUser> currentUserProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        CurrentUser currentUser = currentUserProvider.get();
        if (!currentUser.isAuthenticated()) {
            //((HttpServletResponse)response).sendRedirect("/login");
            Reply.saying().ok().redirect("/login");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
