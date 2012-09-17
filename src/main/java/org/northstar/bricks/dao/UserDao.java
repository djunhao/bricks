package org.northstar.bricks.dao;

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
    void persist(User user);

    int getUserCounts();

    User findById(Object id);

    User findByName(String name);

    List findAll();

    List<User> findPagedUsers(long startIndex, int maxResults);

    List<User> authenticated(String name, String password);
}
