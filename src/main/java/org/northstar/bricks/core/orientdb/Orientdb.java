package org.northstar.bricks.core.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-11-29
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
public class Orientdb {
    private final OObjectDatabaseTx databaseTx;

    public Orientdb(OObjectDatabaseTx databaseTx) {
        this.databaseTx = databaseTx;
    }

    public OObjectDatabaseTx connection() {
        return databaseTx;
    }
}
