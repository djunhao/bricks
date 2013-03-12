package org.northstar.bricks.data.dao;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 13-3-5
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseDao<T, PK extends Serializable> {

    /** Persist the newInstance object into database */
    PK create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
}
