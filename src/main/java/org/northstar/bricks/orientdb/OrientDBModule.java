package org.northstar.bricks.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午1:07
 * To change this template use File | Settings | File Templates.
 */
public class OrientdbModule extends PersistModule {
    private final OrientdbPersistService persistService;
    private final OrientdbProvider dbProvider;

    public OrientdbModule(String url, String user, String password) {
        this(new OrientdbPersistService(url, user, password));
    }

    public OrientdbModule(OObjectDatabaseTx managedDatabase) {
         this(new OrientdbPersistService(managedDatabase));
    }

    public OrientdbModule(OrientdbPersistService persistService) {
        this.persistService = persistService;
        this.dbProvider = new OrientdbProvider(persistService);
    }
    @Override
    protected void configurePersistence() {
        bind(PersistService.class).toInstance(persistService);
        bind(OObjectDatabaseTx.class).toProvider(dbProvider);
    }
}
