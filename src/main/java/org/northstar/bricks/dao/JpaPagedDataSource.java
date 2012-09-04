package org.northstar.bricks.dao;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-1
 * Time: 上午12:03
 * To change this template use File | Settings | File Templates.
 */
public class JpaPagedDataSource<E> implements PagedDataSource<E> {
    @Inject
    private EntityManager entityManager;
    private Class<E> entityType;

    private List<E> preparedResults;

    public JpaPagedDataSource(){}

    public JpaPagedDataSource(final Class<E> entityType) {
        assert entityType != null;
        this.entityType = entityType;
    }

    public int getAvailableRows() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        final Root<E> root = criteriaQuery.from(entityType);
        criteriaQuery = criteriaQuery.select(builder.count(root));
        applyAdditionalConstraints(criteriaQuery, root, builder);
        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    public void prepare(final int firstResult, final int maxResults, String propertyName) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = builder.createQuery(entityType);
        final Root<E> root = criteriaQuery.from(entityType);
        applyAdditionalConstraints(criteriaQuery.select(root), root, builder);
        if (propertyName != null) {
            final Path<Object> propertyPath = root.get(propertyName);
            criteriaQuery.orderBy(builder.desc(propertyPath));
        }

        final TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(firstResult).setMaxResults(maxResults);

        preparedResults = typedQuery.getResultList();
    }

    protected void applyAdditionalConstraints(final CriteriaQuery<?> criteria, final Root<E> root,
                                              final CriteriaBuilder builder) {
    }

    //@Override
    public Iterator<E> iterator() {
        return preparedResults.iterator();
    }
}
