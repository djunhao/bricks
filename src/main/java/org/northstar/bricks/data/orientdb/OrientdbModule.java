package org.northstar.bricks.data.orientdb;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午1:04
 * To change this template use File | Settings | File Templates.
 */
public class OrientdbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PersistService.class).to(OrientdbPersistService.class).in(Singleton.class);
        bind(OObjectDatabaseTx.class).toProvider(OrientdbProvider.class);
    }

}
