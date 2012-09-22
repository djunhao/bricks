package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.domain.Role;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-9
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class OrientRoleDao implements RoleDao {
    @Inject
    Logger logger;

    private OObjectDatabaseTx database;

    public OrientRoleDao() {
    }

    public Role getRoleById(Long id) {
        if(database == null || database.isClosed()) {
            database = getConnection();
        }
        int clusterId = database.getClusterIdByName(Role.class.getSimpleName());
        ORecordId rid = new ORecordId(clusterId, id);
        Role role = database.load(rid);
        database.close();
        return role;
    }

    public Role getRoleByName(String name) {
        if(database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from Role where name = ?";
        OQuery<Role> command = new OSQLSynchQuery<Role>(queryString);
        List<Role> roleList = database.command(command).execute(name);
        Role role = new Role();
        for (Role r : roleList) {
            role = r;
        }
        database.close();
        return role;
    }

    public List<Role> findAll() {
        if(database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from Role";
        OQuery<Role> command = new OSQLSynchQuery<Role>(queryString);
        List<Role> result = database.query(command);
        database.close();
        return result;
    }

    final OObjectDatabaseTx getConnection() {
        //final OObjectDatabaseTx databaseTx = new OObjectDatabaseTx(BricksConstants.ORIENTDB_URL);
        //databaseTx.open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        final OObjectDatabaseTx databaseTx = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        databaseTx.getEntityManager().registerEntityClasses(BricksConstants.ENTITY_PACKAGE);

        return databaseTx;
    }
}
