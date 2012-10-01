package org.northstar.bricks.core.orientdb;

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
 * Date: 12-9-24
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class OrientdbFilter implements Filter {
    private final PersistService persistService;

    @Inject
    public OrientdbFilter(PersistService persistService) {
        this.persistService = persistService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        OObjectDatabaseTx databaseTx = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            databaseTx.close();
        }
    }

    @Override
    public void destroy() {
        OObjectDatabasePool.global().close();
    }
}
