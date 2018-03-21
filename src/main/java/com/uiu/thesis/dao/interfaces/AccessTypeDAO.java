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

    public int addAccessType(AccessType accessType);

    public int updateAccessType(AccessType accessType);

    public int updateAccessType(Long id);

    public int deleteAccessType(AccessType accessType);

    public int deleteAccessType(Long id);

    public AccessType getAccessType(Long id);

    public List<AccessType> getAllAccessTypes();

    public List<AccessType> getAccessTypesByRole(Long roleId);

    public List<AccessType> getAccessTypesByRole(Role role);

    public List<AccessType> getAccessTypesByHR(Long hrId);

    public List<AccessType> getAccessTypesByHR(HumanResource hr);

}
