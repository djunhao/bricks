package org.northstar.bricks.data.dao;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.data.domain.Role;
import org.slf4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-9
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class OrientRoleDao extends AbstractDao implements RoleDao {
    Logger logger;

    private OObjectDatabaseTx database;

    @Inject
    public OrientRoleDao(Injector injector) {
        super(injector);
    }

    public Role getRoleById(Long id) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        int clusterId = database.getClusterIdByName(Role.class.getSimpleName());
        ORecordId rid = new ORecordId(clusterId, id);
        Role role = database.load(rid);
        database.close();
        return role;
    }

    public Role getRoleByName(String name) {
        if (database == null || database.isClosed()) {
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
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from Role";
        OQuery<Role> command = new OSQLSynchQuery<Role>(queryString);
        List<Role> result = database.query(command);
        database.close();
        return result;
    }


}
