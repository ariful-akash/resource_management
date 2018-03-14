package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RoleDAO {

    public boolean addRole(Role role);

    public boolean editRoleDescription(Long roleId, String newDescription);

    public boolean editRoleDescription(Role role, String newDescription);

    public List<Role> getRolesByAccesType(AccessType accessType);

    public Role getRoleByUser(HumanResource user);

    public Role getRoleByUser(Long userId);
}
