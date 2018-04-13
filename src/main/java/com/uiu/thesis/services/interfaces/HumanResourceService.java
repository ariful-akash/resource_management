package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface HumanResourceService {

    public List<HumanResource> getHumanResourcesByType(Long typeId);

    public List<HumanResource> getHumanResourcesByRole(Long roleId);

    public HumanResource getHumanResourceById(Long id);

    public int changeHumanResourceRole(Long hrId, Long roleId);

    public int addHumanResource(HumanResource humanResource);

    public int changeHumanResourceType(Long hrId, Long typeId);

    public int addAccess(Long hrId, Long accessId);

    public int removeAccess(Long hrId, Long accessId);
}
