package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.user.HumanResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface HumanResourceTypeDAO {

    public boolean addHRType(HumanResourceType hrType);

    public boolean updateHRType(HumanResourceType hrType);

    public List<HumanResourceType> getAllHRType();
}
