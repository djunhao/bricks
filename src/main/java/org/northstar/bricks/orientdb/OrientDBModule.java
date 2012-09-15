package org.northstar.bricks.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午1:07
 * To change this template use File | Settings | File Templates.
 */
public class OrientDBModule extends PersistModule {
    private final OrientDBPersistService persistService;
    private final OrientDBProvider dbProvider;

    public OrientDBModule(String url, String user, String password) {
        this(new OrientDBPersistService(url, user, password));
    }

    public OrientDBModule(OObjectDatabaseTx managedDatabase) {
         this(new OrientDBPersistService(managedDatabase));
    }

    public OrientDBModule(OrientDBPersistService persistService) {
        this.persistService = persistService;
        this.dbProvider = new OrientDBProvider(persistService);
    }
    @Override
    protected void configurePersistence() {
        bind(PersistService.class).toInstance(persistService);
        bind(OObjectDatabaseTx.class).toProvider(dbProvider);
    }
}
