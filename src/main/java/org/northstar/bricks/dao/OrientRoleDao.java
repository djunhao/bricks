package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
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
@Singleton
public class OrientRoleDao implements RoleDao {
    @Inject
    Logger logger;

    private final OObjectDatabaseTx database;

    public OrientRoleDao() {
        database = new OObjectDatabaseTx(BricksConstants.ORIENTDB_URL).open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        database.getEntityManager().registerEntityClasses("org.northstar.bricks.domain");
    }

    public Role getRoleById(Object rid) {
        OSQLSynchQuery<Role> query = new OSQLSynchQuery<Role>("select from Role where @rid = ?");
        List<Role> roleList = database.command(query).execute(rid);
        Role role = new Role();
        for (Role r : roleList) {
            role = r;
        }
        //database.close();
        return role;
    }

    public Role getRoleByName(String name) {
        OSQLSynchQuery<Role> query = new OSQLSynchQuery<Role>("select from Role where name = ?");
        List<Role> roleList = database.command(query).execute(name);
        Role role = new Role();
        for (Role r : roleList) {
            role = r;
        }
        //database.close();
        return role;
    }
}
