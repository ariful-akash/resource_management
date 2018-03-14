package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface OfficeResourcesTypeDAO {

    public boolean addOfficeResourceType(OfficeResourceType officeResourceType);

    public boolean updateOfficeResourceType(OfficeResourceType oldType, OfficeResourceType newType);

    public boolean updateOfficeResourceType(Long oldTypeId, OfficeResourceType newType);

    public boolean deleteOfficeResourceType(OfficeResourceType officeResourceType);

    public boolean deleteOfficeResourceType(Long typeId);

    public OfficeResourceType getOfficeResourceType(Long typeId);

    public List<OfficeResourceType> getAllOfficeResourceTypes();
}
