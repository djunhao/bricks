package org.northstar.bricks.core.dao;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.core.domain.User;
import org.slf4j.Logger;

import java.util.List;

@Singleton
public class OrientUserDao extends AbstractDao implements UserDao {
    private Logger logger;
    private OObjectDatabaseTx database;

    @Inject
    public OrientUserDao(Injector injector) {
        super(injector);
    }

    public void save(User user) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        try {
            database.begin(TXTYPE.OPTIMISTIC);
            logger.info(">>> Saving user id: " + user.getId() + " and name: " + user.getLoginName());
            database.save(user);
            database.commit();
        } catch (Exception e) {
            database.rollback();
            e.printStackTrace();
            logger.warn(">>> " + user.getLoginName() + "(" + user.getId() + ")is not saved.");
        } finally {
            database.close();
        }
    }

    public void delete(User user) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        try {
            database.begin(TXTYPE.OPTIMISTIC);
            database.delete(user);
            database.commit();
        } catch (Exception e) {
            database.rollback();
        } finally {
            database.close();
        }
    }

    public int getUserCounts() {
        logger.info(">>> getUserCounts() excuted...");
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        long count = database.countClass(User.class);
        database.close();
        return (int) count;
    }

    public User findByName(String name) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from User where name = ?";
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);

        List<User> result = database.command(command).execute(name);
        User user = new User();
        for (User u : result) {
            user = u;
        }
        database.close();
        return user;
    }

    public List<User> findAll() {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from User";
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);
        List<User> result = database.query(command);
        database.close();
        return result;
    }

    public List<User> findPagedUsers(long startIndex, int maxResults) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        String queryString = "select from User where @rid > ? limit " + maxResults;
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);
        int clusterId = database.getClusterIdByName(User.class.getSimpleName());

        ORID startRid = new ORecordId(clusterId, startIndex);
        //List<User> userList = database.command(query).execute(startRid);
        List<User> userList = database.query(command, startRid);
        database.close();
        return userList;
    }

    public List<User> authenticated(String loginName, String password) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        /*
        //for learn
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("name", name);
     params.put("password", password);
     List<User> result = db.command(query).execute(params);*/
        String queryString = "select from User where loginName = ? and password = ?";
        OQuery<User> command = new OSQLSynchQuery<User>(queryString);
        List<User> result = database.query(command, loginName, password);
        /*User user = new User();
        for (User u : result) {
            user = u;
        }*/
        database.close();
        return result;
    }

    public User findById(Long id) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        int clusterId = database.getClusterIdByName(User.class.getSimpleName());
        ORID rid = new ORecordId(clusterId, id);
        User user = database.load(rid);
        database.close();
        return user;
    }

    public User findById(String id) {
        if (database == null || database.isClosed()) {
            database = getConnection();
        }
        ORID rid = new ORecordId(id);
        User user = database.load(rid);
        database.close();
        return user;
    }
}

