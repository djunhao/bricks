package org.northstar.bricks.dao;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.northstar.bricks.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDao {

    @Inject
    private EntityManager em;

    @Transactional
    public void createNewUser(User user) {
        em.persist(user);
    }

    public int getUserCounts() {
        System.out.println("getUserCount executed...");
        Query query = em.createQuery("select count(u) from User u");
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }
}
