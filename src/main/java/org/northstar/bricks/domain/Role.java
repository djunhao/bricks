package org.northstar.bricks.domain;

import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-8
 * Time: 下午11:03
 * To change this template use File | Settings | File Templates.
 */
public class Role {
    @Id
    private Object rid;

    private String name;
    private byte mode;

    public Role() {

    }

    public Object getId() {
        return rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (mode != role.mode) return false;
        if (rid != null ? !rid.equals(role.rid) : role.rid != null) return false;
        if (!name.equals(role.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) mode;
        return result;
    }
}
