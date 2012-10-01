package org.northstar.bricks.core.domain;

import javax.persistence.Id;
import javax.persistence.Version;

public class User {
    @Id
    private Long id;
    @Version
    private Object version; //This field with @Version is for database transaction operation.

    private String name;
    private String password;
    private Role role;

    public User(){}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

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
