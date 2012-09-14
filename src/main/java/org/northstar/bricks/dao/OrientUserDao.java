package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.domain.User;

import java.util.List;
import java.util.logging.Logger;

@Singleton
public class OrientUserDao implements UserDao {
    @Inject
    private Logger logger;

    @Inject
    private OObjectDatabaseTx database;
   /* = OObjectDatabasePool.global().acquire(
            BricksConstants.ORIENTDB_URL,
            BricksConstants.ORIENTDB_USER,
            BricksConstants.ORIENTDB_PASSWORD);*/

    public OrientUserDao() {
        //database = new OObjectDatabaseTx(BricksConstants.ORIENTDB_URL).open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        database.getEntityManager().registerEntityClasses(BricksConstants.ENTITY_PACKAGE);
    }

    public void createNewUser(User user) {
        database.newInstance(User.class);
        database.save(user);
        //database.close();
    }

    public int getUserCounts() {
        long count = database.countClass(User.class);
        logger.info("User counts is: " + count);
        //database.close();
        return (int) count;
    }

    public List<User> findAll() {
        List<User> result = database.query(new OSQLSynchQuery<User>("select from User"));
        return result;
        //database.close();
    }

    public List<User> findPagedUsers(long startIndex, int maxResults) {
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where @rid > ? limit " + maxResults);

        int clusterId = database.getClusterIdByName(User.class.getSimpleName());
        ORID startRid = new ORecordId(clusterId, startIndex);
        //List<User> userList = database.command(query).execute(startRid);
        List<User> userList = database.query(query, startRid);

        logger.info("get RID: " + startRid);
        //database.close();
        return userList;
    }

    public List<User> authenticated(String name, String password) {
        /*OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("name", name);
     params.put("password", password);
     List<User> result = db.command(query).execute(params);*/

        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name = ? and password = ?");
        List<User> result = database.command(query).execute(name, password);
        for (User u : result) {
            logger.info("User Rid is:" + u.getRid() + " and name is:" + u.getName());
        }
        return result;
        //database.close();
    }
}

