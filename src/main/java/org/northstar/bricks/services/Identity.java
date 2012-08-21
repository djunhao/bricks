package org.northstar.bricks.services;

import com.google.inject.servlet.SessionScoped;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-8-16
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */

@SessionScoped
public class Identity {
    private Long uid;
    private String name;

    public boolean isAuthenticate()
    {
        return uid != null;
    }

    public void logout()
    {
        this.uid = null;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
