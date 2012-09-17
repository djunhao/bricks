package org.northstar.bricks.orientdb;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-15
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 */
public interface OrientdbManager {
    void persist(Object entity);
    void remove(Object entity);
    <T> T find(Class<T> entityClass, Object primaryKey);
    <T> T find(Class<T> entityClass);;
    <T> T find(String className);
    void commit();
    void rollback();
    void close();
    boolean isOpen();
    long count();
}
