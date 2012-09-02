package org.northstar.bricks.dao;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-31
 * Time: 下午11:57
 * To change this template use File | Settings | File Templates.
 */
public interface PagedDataSource<E> extends Iterable<E> {

    int getAvailableRows();

    void prepare(int firsResult, int maxResults, String propertyName);

}
