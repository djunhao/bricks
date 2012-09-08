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

    private String type;
    private byte mode;

    public Object getRid() {
        return rid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }
}
