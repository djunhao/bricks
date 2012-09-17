package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction;
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

    public OrientUserDao() {
    }

    public void saveOrUpdate(User user) {
        if (database == null) {
            database = getConnection();
        }
        try {
            database.begin(OTransaction.TXTYPE.OPTIMISTIC);
            database.newInstance(User.class);
            database.save(user);
            database.commit();
        } catch (Exception e) {
            database.rollback();
        } /*finally {
            if (database != null) {
                database.close();
            }
        }*/
    }

    public int getUserCounts() {
        database = getConnection();
        long count = database.countClass(User.class);
        //database.close();
        return (int) count;
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
        database = getConnection();
        String queryString = "select from User";
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);
        List<User> result = database.query(command);
        //database.close();
        return result;
    }

    public List<User> findPagedUsers(long startIndex, int maxResults) {
        database = getConnection();
        String queryString = "select from User where @rid > ? limit " + maxResults;
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);
        int clusterId = database.getClusterIdByName(User.class.getSimpleName());
        ORID startRid = new ORecordId(clusterId, startIndex);
        //List<User> userList = database.command(query).execute(startRid);
        List<User> userList = database.query(command, startRid);
        //database.close();
        return userList;
    }

    public List<User> authenticated(String name, String password) {
        /*
        //for learn
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("name", name);
     params.put("password", password);
     List<User> result = db.command(query).execute(params);*/
        database = getConnection();
        String queryString = "select from User where name = ? and password = ?";
        OQuery<User> command =  new OSQLSynchQuery<User>(queryString);
        List<User> result = database.command(command).execute(name, password);
        /*User user = new User();
        for (User u : result) {
            user = u;
        }*/
        //database.close();
        return result;
    }

    public User findById(Long id) {
        database = getConnection();
        int clusterId = database.getClusterIdByName(User.class.getSimpleName());
        ORID rid = new ORecordId(clusterId, id);
        User user = database.load(rid);
        //database.close();
        return user;
    }

    final OObjectDatabaseTx getConnection() {
        final OObjectDatabaseTx databaseTx = new OObjectDatabaseTx(BricksConstants.ORIENTDB_URL);
        databaseTx.open(BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        databaseTx.getEntityManager().registerEntityClasses(BricksConstants.ENTITY_PACKAGE);
        return databaseTx;
    }
}

