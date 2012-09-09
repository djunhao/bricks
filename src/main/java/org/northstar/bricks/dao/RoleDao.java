package org.northstar.bricks.dao;

import org.northstar.bricks.domain.Role;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-9
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDao {
    Role getRoleById(Object rid);
    Role getRoleByType(String type);
}
