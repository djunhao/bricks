package org.northstar.bricks.domain;

import com.google.inject.servlet.SessionScoped;
import com.orientechnologies.orient.core.annotation.OId;

public class User {
    @OId
    private Long id;

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
