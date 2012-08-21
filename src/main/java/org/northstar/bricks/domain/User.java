package org.northstar.bricks.domain;

import java.io.Serializable;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "getAllUsers", query = "Select u From User u"),
        @NamedQuery(name = "getUserByName", query = "Select u From User u Where u.name = :name"),
        @NamedQuery(name = "getUserById", query = "Select u From User u Where u.id = :id"),
        @NamedQuery(name = "getUserByNamePwd", query = "select u from User u where u.name=:name and u.password=:password"),
        @NamedQuery(name = "getPagedUsers", query = "Select u From User u")
        })
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 7158126725092237523L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
