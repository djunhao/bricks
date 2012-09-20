package org.northstar.bricks.domain;

import com.google.inject.servlet.SessionScoped;
import com.orientechnologies.orient.core.annotation.OId;

import javax.persistence.Id;
import javax.persistence.Version;

public class User {
    @Id
    private Object id;
    @Version
    private Object version;

    private String name;
    private String password;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
