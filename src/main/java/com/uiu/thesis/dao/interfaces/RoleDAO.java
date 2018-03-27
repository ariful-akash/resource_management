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

    public int addRole(Role role);

    public int editRoleDescription(Long roleId, String newDescription);

    public int editRoleDescription(Role role, String newDescription);

    public int updateRole(Role role);

    public List<Role> getRolesByAccesType(AccessType accessType);

    public Role getRoleByUser(HumanResource user);

    public Role getRoleByUser(Long userId);

    public Role getRoleById(Long roleId);

    public Role getRoleByName(String roleName);

    public List<Role> getAllRoles();
}
