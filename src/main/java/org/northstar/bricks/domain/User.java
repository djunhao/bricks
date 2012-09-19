package org.northstar.bricks.domain;

import com.google.inject.servlet.SessionScoped;
import com.orientechnologies.orient.core.annotation.OId;

import javax.persistence.Id;

public class User {
    @Id
    private Long id;
    /*@OId
    private Object rid;*/

    private String name;
    private String password;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
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
