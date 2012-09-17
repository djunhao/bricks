package org.northstar.bricks.orientdb;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-11
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class OrientdbFilter implements Filter {
    private final PersistService persistService;

    @Inject
    public OrientdbFilter(PersistService persistService) {
        this.persistService = persistService;
    }
    public void init(FilterConfig filterConfig) throws ServletException {
        persistService.start();
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        } finally {
            persistService.stop();
        }
    }

    public void destroy() {
        persistService.stop();
    }
}
