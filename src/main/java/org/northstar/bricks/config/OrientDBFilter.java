package org.northstar.bricks.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.northstar.bricks.orientdb.PersistService;
import org.northstar.bricks.orientdb.UnitOfWork;

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
public class OrientDBFilter implements Filter {
    private final PersistService persistService;
    private final UnitOfWork unitOfWork;

    @Inject
    public OrientDBFilter(PersistService persistService, UnitOfWork unitOfWork) {
        this.persistService = persistService;
        this.unitOfWork = unitOfWork;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        persistService.start();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        unitOfWork.begin();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            unitOfWork.end();
        }
    }

    public void destroy() {
        persistService.stop();
    }
}
