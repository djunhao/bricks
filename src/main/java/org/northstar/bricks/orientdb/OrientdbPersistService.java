package org.northstar.bricks.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午12:27
 * To change this template use File | Settings | File Templates.
 */
public class OrientdbPersistService implements PersistService {
    private final String url;
    private final String user;
    private final String password;
    private final OObjectDatabaseTx managedDatabase;
    private volatile OObjectDatabaseTx localDatabase;

    public OrientdbPersistService(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.managedDatabase = null;
    }

    public OrientdbPersistService(OObjectDatabaseTx database) {
        this.url = null;
        this.user = null;
        this.password = null;
        this.managedDatabase = database;
    }

    public synchronized boolean start() {
        if (null != managedDatabase || null != localDatabase) {
            return false;
        }
        this.localDatabase = createDatabaseConnection(url, user, password);
        return true;
    }

    public synchronized void stop() {
        if (null != localDatabase) {
            localDatabase.close();
            localDatabase = null;
        }
    }

    OObjectDatabaseTx getDatabaseConnection() {
        final OObjectDatabaseTx databaseTx = null != managedDatabase ? managedDatabase : localDatabase;
        return databaseTx;
    }

    OObjectDatabaseTx createDatabaseConnection(String url, String user, String password) {
        OObjectDatabaseTx databaseTx = new OObjectDatabaseTx(url);
        databaseTx.open(user, password);
        return databaseTx;
    }
}
