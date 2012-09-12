package org.northstar.bricks.config;

import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-11
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
public class OrientDBFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) {
        OObjectDatabaseTx database = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        try{
            chain.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            database.close();
        }
    }

    public void destroy() {
        OObjectDatabasePool.global().close();
    }
}
