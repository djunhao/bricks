package org.northstar.bricks.core.domain;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

public class User {
    @Id
    private Long id;
    @Version
    private Object version; //This field with @Version is for database transaction operation.

    private String loginName;
    private String password;
    private String realName;
    private Role role;
    private String enforcementNumber;
    private Date validEnforcementDate;
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

    //如果页面类使用单例模式，则无需此方法
    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getEnforcementNumber() {
        return enforcementNumber;
    }

    public void setEnforcementNumber(String enforcementNumber) {
        this.enforcementNumber = enforcementNumber;
    }

    public Date getValidEnforcementDate() {
        return validEnforcementDate;
    }

    public void setValidEnforcementDate(Date validEnforcementDate) {
        this.validEnforcementDate = validEnforcementDate;
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
