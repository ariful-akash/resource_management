package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface AccessTypeDAO {

    public boolean addAccessType(AccessType accessType);

    public boolean updateAccessType(AccessType accessType);

    public boolean updateAccessType(Long id);

    public boolean deleteAccessType(AccessType accessType);

    public boolean deleteAccessType(Long id);

    public List<AccessType> getAllAccessTypes();

    public List<AccessType> getAccessTypesByRole(Long roleId);

    public List<AccessType> getAccessTypesByRole(Role role);

    public List<AccessType> getAccessTypesByHR(Long hrId);

    public List<AccessType> getAccessTypesByHR(HumanResource hr);

}
