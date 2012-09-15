package org.northstar.bricks.orientdb;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午1:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class PersistModule extends AbstractModule {

    @Override
    protected void configure() {
        configurePersistence();

        requireBinding(PersistService.class);
    }

    protected abstract void configurePersistence();
}
