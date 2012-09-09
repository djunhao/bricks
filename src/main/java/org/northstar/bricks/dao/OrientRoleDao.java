package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.domain.Role;
import org.northstar.bricks.domain.User;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-9
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
public class OrientRoleDao implements RoleDao {
    @Inject
    Logger logger;

    private OObjectDatabaseTx database;

    public OrientRoleDao() {
        database = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        database.getEntityManager().registerEntityClasses("org.northstar.bricks.domain");
    }

    public Role getRoleById(Object rid) {
        OSQLSynchQuery<Role> query = new OSQLSynchQuery<Role>("select from Role where @rid = ?");
        List<Role> roleList = database.query(query, rid);
        Role role = new Role();
        for (Role r : roleList) {
            role = r;
        }
        return role;
    }

    public Role getRoleByType(String type) {
        OSQLSynchQuery<Role> query = new OSQLSynchQuery<Role>("select distinct(type) from Role where type = ?");
        List<Role> roleList = database.query(query, type);
        Role role = new Role();
        for (Role r : roleList) {
            role = r;
        }
        return role;
    }
}
