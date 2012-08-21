package org.northstar.bricks.services;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class SecurityFilter implements Filter {

    private Injector injector;

    @Inject
    public SecurityFilter(Injector injector){
        this.injector = injector;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)
    {
        Identity identity = injector.getInstance(Identity.class);

        if(identity.isAuthenticate())
        {
            System.err.println("USER");
        }
        else
        {
            System.err.println("GUEST");
        }

        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
