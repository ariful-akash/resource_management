package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
import java.util.List;

/**
 *
 * @author ashif
 */
public class RoleDAOImpl implements RoleDAO {

    @Override
    public boolean addRole(Role role) {

        return false;
    }

    @Override
    public boolean editRoleDescription(Long roleId, String newDescription) {

        return false;
    }

    @Override
    public boolean editRoleDescription(Role role, String newDescription) {

        return false;
    }

    @Override
    public List<Role> getRolesByAccesType(AccessType accessType) {

        return null;
    }

    @Override
    public Role getRoleByUser(HumanResource user) {

        return null;
    }

    @Override
    public Role getRoleByUser(Long userId) {

        return null;
    }

}
