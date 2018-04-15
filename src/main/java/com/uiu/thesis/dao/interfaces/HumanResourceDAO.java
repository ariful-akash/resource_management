package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ashif
 */
public interface HumanResourceDAO {

    public int addHumanResource(HumanResource hr);

    public int updateHumanResource(HumanResource hr);

    public int updateHumanResourceRole(Long hrId, Long roleId);

    public boolean isRoleRelatesHR(Long hrId, Long roleId);

    public int mapHumanResourceRole(Long hrId, Long roleId);

    public int addHumanResourceAccess(Long hrId, Long accessId);

    public int removeHumanResourceAccess(Long hrId, Long accessId);

    public int updateHumanResource(HumanResource hr, Set<AccessType> accessTypes);

    public int updateHumanResourceType(Long hrId, Long typeId);

    public int deleteHumanResource(HumanResource hr);

    public HumanResource getHumanResource(Long id);

    public HumanResource getHumanResource(String email);

    public List<HumanResource> getAllHumanResources();

    public List<HumanResource> getHumanResourcesByHRType(Long hrTypeId);

    public List<HumanResource> getHumanResourcesByRole(Long roleId);

    public List<HumanResource> getHumanResourcesByAccessType(Long accessTypeId);
}
