package org.northstar.bricks.core.orientdb;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
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
    private final Provider<OObjectDatabaseTx> connectionProvider;

    @Inject
    public OrientdbFilter(Provider<OObjectDatabaseTx> connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final OObjectDatabaseTx databaseTx = connectionProvider.get();
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
