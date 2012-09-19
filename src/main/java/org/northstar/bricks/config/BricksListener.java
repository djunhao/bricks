package org.northstar.bricks.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Configures Guice injector
 *
 * @author
 */
public class BricksListener extends GuiceServletContextListener {
    protected Injector getInjector() {
        System.out.println("\n>>>[BIRCKS] Sitebricks web application demo started.");
        return Guice.createInjector(Stage.DEVELOPMENT, new BricksModule());
    }
}