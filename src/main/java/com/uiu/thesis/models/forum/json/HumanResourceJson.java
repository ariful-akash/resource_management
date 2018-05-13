package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.uiu.thesis.models.user.HumanResource;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class HumanResourceJson extends HumanResource {

    private String designation;

    private long roleId;

    public HumanResourceJson() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
