package org.northstar.bricks.dao;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.northstar.bricks.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-8
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {

    void save(User user);

    void update(User user, Long id);

    int getUserCounts();

    User findByName(String name);

    List findAll();

    List<User> findPagedUsers(long startIndex, int maxResults);

    List<User> authenticated(String name, String password);

    User findById(Long id);
    User findById(String id);

    OObjectDatabaseTx getConnection();
}
