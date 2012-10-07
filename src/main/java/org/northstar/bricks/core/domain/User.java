package org.northstar.bricks.core.domain;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

public class User {
    @Id
    private Long id;
    @Version
    private Object version; //This field with @Version is for database transaction operation.

    private String name;
    private String password;
    private String realName;
    private Role role;
    private String enforceMentNumber;
    private Date validEnforceMentDate;
    private String supervisorNumber;
    private Date validSupervisorDate;

    public User() {
    }

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEnforceMentNumber() {
        return enforceMentNumber;
    }

    public void setEnforceMentNumber(String enforceMentNumber) {
        this.enforceMentNumber = enforceMentNumber;
    }

    public Date getValidEnforceMentDate() {
        return validEnforceMentDate;
    }

    public void setValidEnforceMentDate(Date validEnforceMentDate) {
        this.validEnforceMentDate = validEnforceMentDate;
    }

    public String getSupervisorNumber() {
        return supervisorNumber;
    }

    public void setSupervisorNumber(String supervisorNumber) {
        this.supervisorNumber = supervisorNumber;
    }

    public Date getValidSupervisorDate() {
        return validSupervisorDate;
    }

    public void setValidSupervisorDate(Date validSupervisorDate) {
        this.validSupervisorDate = validSupervisorDate;
    }
}
