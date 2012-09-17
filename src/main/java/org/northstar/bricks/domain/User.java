package org.northstar.bricks.domain;

import com.google.inject.servlet.SessionScoped;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import net.jcip.annotations.ThreadSafe;

import javax.persistence.Id;

@SessionScoped
@ThreadSafe
public class User {
    @Id
    private Object rid;

    private String name;
    private String password;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Object getRid() {
        return rid;
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

    public boolean isAuthenticated() {
        return this.name != null;
    }

    public void logout() {
        this.name = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!rid.equals(user.rid)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
