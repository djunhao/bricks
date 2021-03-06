package org.northstar.bricks.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.sitebricks.slf4j.Slf4jModule;
import org.northstar.bricks.data.orientdb.OrientdbModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configures Guice injector
 *
 * @author
 */
public class BricksListener extends GuiceServletContextListener {
    private Logger logger = LoggerFactory.getLogger(BricksListener.class);

    protected Injector getInjector() {
        logger.info(">>>[BIRCKS] Sitebricks web application demo started.");
        return Guice.createInjector(Stage.DEVELOPMENT, new Slf4jModule(), new BricksModule(), new OrientdbModule());
    }
}