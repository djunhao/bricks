package org.northstar.bricks.services;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-13
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public interface UnitOfWork {

    void begin();

    void end();

    boolean isActive();
}
