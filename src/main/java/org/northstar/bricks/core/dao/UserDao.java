package org.northstar.bricks.core.dao;

import org.northstar.bricks.core.domain.User;

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

    void delete(User user);

    int getUserCounts();

    User findByName(String name);

    List<User> findAll();

    List<User> findPagedUsers(long startIndex, int maxResults);
    List<User> findUsers(long startIndex, int maxResults);

    List<User> authenticated(String name, String password);

    User findById(Long id);

    User findById(String id);
}
