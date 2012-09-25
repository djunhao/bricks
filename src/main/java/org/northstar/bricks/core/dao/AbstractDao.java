package org.northstar.bricks.core.dao;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-25
 * Time: 上午8:43
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDao {
    protected Injector injector;

    public AbstractDao(Injector injector) {
        this.injector = injector;
    }

    protected OObjectDatabaseTx getConnection() {
        Provider<OObjectDatabaseTx> connectionProvider = injector.getProvider(OObjectDatabaseTx.class);
        return connectionProvider.get();
    }
}
