package org.northstar.bricks.data.domain;

import com.orientechnologies.orient.core.annotation.OId;

/**
 * Created with IntelliJ IDEA.
 * User: dfamily
 * Date: 12-9-8
 * Time: 下午11:03
 * To change this template use File | Settings | File Templates.
 */
public class Role {
    @OId
    private Long rid;

    private String name;
    private byte mode;

    public Role() {
    }

    public Long getRid() {
        return rid;
    }

    /* public void setRid(Long rid) {
        this.rid = rid;
    }*/

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
}
