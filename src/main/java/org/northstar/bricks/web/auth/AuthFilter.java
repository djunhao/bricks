package org.northstar.bricks.web.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
        // Don't allow the anonymous user to get in to this site.
        CurrentUser currentUser = currentUserProvider.get();
        // First see if there is a session cookie.
        Cookie sessionCookie = currentUser.getSessionCookie();
        if (null == sessionCookie) {
            // Auth as anonymous.
            currentUser.setUser(null);
        }
        if (!currentUser.isAuthenticated()) {
            ((HttpServletResponse) response).sendRedirect("/login");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
