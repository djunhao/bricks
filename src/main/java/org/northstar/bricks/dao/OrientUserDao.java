package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.config.BricksConstants;
import org.northstar.bricks.domain.User;

import java.util.List;
import java.util.logging.Logger;

@Singleton
public class OrientUserDao implements UserDao {
    @Inject
    private Logger logger;

    private final OObjectDatabaseTx database;

    public OrientUserDao() {
        database = OObjectDatabasePool.global().acquire(BricksConstants.ORIENTDB_URL, BricksConstants.ORIENTDB_USER, BricksConstants.ORIENTDB_PASSWORD);
        database.getEntityManager().registerEntityClasses("org.northstar.bricks.domain");
    }

    public void createNewUser(User user) {
        database.newInstance(User.class);
        database.save(user);
        //database.close();
    }

    public int getUserCounts() {
        logger.info("getUserCounts() executed...");
        long count = database.countClass(User.class);
        //database.close();
        return (int) count;
    }

    public List<User> findAll() {
        List<User> result = database.query(new OSQLSynchQuery<User>("select from User"));
        //database.close();
        return result;
    }

    public List<User> findPagedUsers() {
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User limit 5");
        List<User> userList = database.query(query);
        for (List<User> result = database.query(query); !result.isEmpty(); result = database.query(query)) {
            userList = result;
        }
        //database.close();
        return userList;
    }

    public List<User> authenticated(String name, String password) {
        /*OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("password", password);
        List<User> result = db.command(query).execute(params);*/
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select distinct(name) from User where name = ? and password = ?");
        List<User> result = database.command(query).execute(name, password);
        for (User u : result) {
            logger.info("User Rid is:" + u.getRid() + " and name is:" + u.getName());
        }
        //database.close();
        return result;
    }
}
