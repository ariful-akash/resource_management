package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
import com.uiu.thesis.models.user.Role;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface HumanResourceDAO {

    public int addHumanResource(HumanResource hr);

    public int updateHumanResource(HumanResource oldHr, HumanResource newHr);

    public int deleteHumanResource(HumanResource hr);

    public HumanResource getHumanResource(Long id);

    public List<HumanResource> getAllHumanResources();

    public List<HumanResource> getHumanResources(HumanResourceType hrType);

    public List<HumanResource> getHumanResources(Role role);

    public List<HumanResource> getHumanResources(AccessType accessType);
}
