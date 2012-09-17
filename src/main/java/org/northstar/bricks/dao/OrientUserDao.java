package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
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

    private OObjectDatabaseTx database;
    /* = OObjectDatabasePool.global().acquire(
    BricksConstants.ORIENTDB_URL,
    BricksConstants.ORIENTDB_USER,
    BricksConstants.ORIENTDB_PASSWORD);*/

    public OrientUserDao() {
        database = new OObjectDatabaseTx(BricksConstants.ORIENTDB_URL).open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        database.getEntityManager().registerEntityClasses(BricksConstants.ENTITY_PACKAGE);
    }

    public void persist(User user) {
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

    public User findById(Object id) {
        ORecordId rid = (ORecordId) id;
        return database.load(rid);
    }

    public User findByName(String name) {
        String queryString = "select from User where name = ?";
        OQuery<User> command = new OSQLSynchQuery<>(queryString);

        List<User> result = database.query(command, name);
        User user = new User();
        for (User u : result) {
            user = u;
        }
        return user;
    }

    public List<User> findAll() {
        String queryString = "select from User";
        OQuery<User> command = new OSQLSynchQuery<>(queryString);
        List<User> result = database.query(command);
        return result;
        //database.close();
    }

    public List<User> findPagedUsers(long startIndex, int maxResults) {
        String queryString = "select from User where @rid > ? limit " + maxResults;
        OQuery<User> command = new OSQLSynchQuery<>(queryString);
        int clusterId = database.getClusterIdByName(User.class.getSimpleName());
        ORID startRid = new ORecordId(clusterId, startIndex);
        //List<User> userList = database.command(query).execute(startRid);
        List<User> result = database.query(command, startRid);
        //database.close();
        return result;
    }

    public List<User> authenticated(String name, String password) {
        /*
        //for learn
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("name", name);
     params.put("password", password);
     List<User> result = db.command(query).execute(params);*/
        String queryString = "select from User where name = ? and password = ?";
        OQuery<User> command = new OSQLSynchQuery<>(queryString);
        List<User> result = database.query(command, name, password);
        return result;
        //database.close();
    }
}

