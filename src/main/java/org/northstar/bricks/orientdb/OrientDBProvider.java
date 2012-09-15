package org.northstar.bricks.orientdb;

import com.google.inject.Provider;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午1:00
 * To change this template use File | Settings | File Templates.
 */
public class OrientDBProvider implements Provider<OObjectDatabaseTx> {
    private final OrientDBPersistService orientDBPersistService;

    public OrientDBProvider(OrientDBPersistService orientDBPersistService) {
        this.orientDBPersistService = orientDBPersistService;
    }

    @Override
    public OObjectDatabaseTx get() {
        return orientDBPersistService.getDatabaseConnection();
    }
}
