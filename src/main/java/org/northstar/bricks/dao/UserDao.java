package org.northstar.bricks.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.northstar.bricks.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class UserDao {
    @Inject
    private EntityManager entityManager;
    @Inject
    private Logger logger;

    @Transactional
    public void createNewUser(User user) {
        entityManager.persist(user);
    }

    public int getUserCounts() {
        logger.info("getUserCounts() executed...");
        /*Query query = em.createQuery("select count(u) from User u");
        Number result = (Number) query.getSingleResult();*/
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(builder.count(root));

        Long result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return result.intValue();
    }
}
