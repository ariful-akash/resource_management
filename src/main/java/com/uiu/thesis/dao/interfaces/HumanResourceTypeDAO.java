package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.HumanResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface HumanResourceTypeDAO {

    public int addHRType(HumanResourceType hrType);

    public int updateHRType(HumanResourceType hrType);

    public HumanResourceType getHRTypeByHRId(Long hrId);

    public List<HumanResourceType> getAllHRType();

    public HumanResourceType getHumanResourceType(Long hrTypeId);

    public HumanResourceType getHumanResourceType(String resourceName);
}
