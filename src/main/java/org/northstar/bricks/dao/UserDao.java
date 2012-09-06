package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {
    @Inject
    private Logger logger;

    public UserDao() {
    }

    public void createNewUser(User user) {
        OObjectDatabaseTx db = new OObjectDatabaseTx("remote:localhost/bricks").open("admin", "admin");
        db.getEntityManager().registerEntityClass(User.class);
        User u = db.newInstance(User.class);
        u = user;
        db.save(u);
        db.close();
    }

    public int getUserCounts() {
        logger.info("getUserCounts() executed...");
        OObjectDatabaseTx db = new OObjectDatabaseTx("remote:localhost/bricks").open("admin", "admin");
        db.getEntityManager().registerEntityClass(User.class);
        long count = db.countClass(User.class);
        db.close();
        return (int) count;
    }

    public List<User> findAll() {
        OObjectDatabaseTx db = new OObjectDatabaseTx("remote:localhost/bricks").open("admin", "admin");
        db.getEntityManager().registerEntityClass(User.class);
        List<User> result = db.query(new OSQLSynchQuery<User>("select from User"));
        db.close();
        return result;
    }

    public List<User> findPagedUsers() {
        ODatabaseDocumentTx db = new OObjectDatabaseTx("remote:localhost/bricks").open("admin", "admin");
        //db.getEntityManager().registerEntityClass(User.class);

        OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>("select from User limit 5");
        List<User> userList = new ArrayList<User>();
        for (List<ODocument> result = db.query(query); !result.isEmpty();result = db.query(query)){
            User user = new User();
            for (ODocument doc : result){
                user.setName(String.valueOf(doc.field("name")));
                user.setPassword(String.valueOf(doc.field("password")));
                user.setId((Long) doc.field("id"));
                userList.add(user);
            }
        }
        db.close();
        return userList;
    }

    public List<User> authenticated(String name, String password) {
        OObjectDatabaseTx db = new OObjectDatabaseTx("remote:localhost/bricks").open("admin", "admin");
        db.getEntityManager().registerEntityClass(User.class);
        /*OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select from User where name=:name and password=:password");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("password", password);
        List<User> result = db.command(query).execute(params);*/
        OSQLSynchQuery<User> query = new OSQLSynchQuery<User>("select * from User where name = ? and password = ?");
        List<User> result = db.command(query).execute(name, password);
        for (User u : result){
            logger.info("User Id is:" + u.getId()+" and name is:" + u.getName());
        }
        db.close();
        return result;
    }
}
