package org.northstar.bricks.data.dao;

import org.northstar.bricks.data.domain.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-9
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDao {
    Role getRoleById(Long rid);

    Role getRoleByName(String name);

    List<Role> findAll();
}
