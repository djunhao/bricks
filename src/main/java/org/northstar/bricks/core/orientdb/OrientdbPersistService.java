package org.northstar.bricks.core.orientdb;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.exception.ORecordNotFoundException;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-15
 * Time: 上午12:27
 * To change this template use File | Settings | File Templates.
 */
public class OrientdbPersistService implements PersistService {
    @Inject
    private Logger logger;

    private final String url = BricksConstants.ORIENTDB_URL;
    private final String user = BricksConstants.ORIENTDB_USER;
    private final String password = BricksConstants.ORIENTDB_PASSWORD;
    private final OObjectDatabaseTx managedDatabase;
    private volatile OObjectDatabaseTx localDatabase;

    public OrientdbPersistService() {
        this.managedDatabase = null;
    }

    public OrientdbPersistService(OObjectDatabaseTx database) {
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
        int maxRetry = 100; // give it up to approx 10 seconds to recover
        int retryCount = 0;
        OObjectDatabaseTx databaseTx = null;
        while (databaseTx == null && retryCount < maxRetry) {
            retryCount++;
            try {
                databaseTx = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
                if (retryCount > 1) {
                    logger.info("Succeeded in acquiring connection from pool in retry attempt " + retryCount);
                }
                retryCount = maxRetry;
            } catch (ORecordNotFoundException ex) {
                if (retryCount == maxRetry) {
                    logger.warning("Failure reported acquiring connection from pool, retried " + retryCount + " times before giving up.");
                } else {
                    logger.info("Pool acquire reported failure, retrying - attempt " + retryCount);
                    try {
                        Thread.sleep(100); // Give the DB time to complete what it's doing before retrying
                    } catch (InterruptedException iex) {
                        // ignore that sleep was interrupted
                    }
                }
            }

        }
        databaseTx.getEntityManager().registerEntityClasses(BricksConstants.ENTITY_PACKAGE);
        return databaseTx;
    }
}
