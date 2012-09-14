package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.persist.finder.Finder;
import com.google.inject.persist.finder.FirstResult;
import com.google.inject.persist.finder.MaxResults;
import org.northstar.bricks.domain.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 北辰
 * Date: 12-9-12
 * Time: 下午11:54
 * To change this template use File | Settings | File Templates.
 */
public class OrientJpaUserDao implements UserDao {
    @Inject
    private EntityManager em;

    public void createNewUser(User user) {
       em.persist(user);
    }

    public int getUserCounts() {
        Long result = (Long) em.createQuery("select count(*) from User").getSingleResult();
        return result.intValue();
    }

    @Finder(query = "select from User", returnAs = ArrayList.class)
    public List<User> findAll() {
        return null;
    }

    @Finder(query = "select from User", returnAs = ArrayList.class)
    public List<User> findPagedUsers(@FirstResult long startIndex, @MaxResults int maxResults) {
        return null;
    }

    @Override
    public List<User> authenticated(String name, String password) {
        return null;
    }
}
