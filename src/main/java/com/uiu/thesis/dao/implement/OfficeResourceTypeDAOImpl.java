package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
public class OfficeResourceTypeDAOImpl implements OfficeResourceTypeDAO {

    @Override
    public boolean addOfficeResourceType(OfficeResourceType officeResourceType) {

        return false;
    }

    @Override
    public boolean updateOfficeResourceType(OfficeResourceType oldType, OfficeResourceType newType) {

        return false;
    }

    @Override
    public boolean updateOfficeResourceType(Long oldTypeId, OfficeResourceType newType) {

        return false;
    }

    @Override
    public boolean deleteOfficeResourceType(OfficeResourceType officeResourceType) {

        return false;
    }

    @Override
    public boolean deleteOfficeResourceType(Long typeId) {

        return false;
    }

    @Override
    public OfficeResourceType getOfficeResourceType(Long typeId) {

        return null;
    }

    @Override
    public List<OfficeResourceType> getAllOfficeResourceTypes() {

        return null;
    }

}
