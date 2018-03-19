package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface OfficeResourceDAO {

    public int addOfficeResource(OfficeResource officeResource);

    public boolean updateOfficeResource(OfficeResource oldOfficeResource, OfficeResource newOfficeResource);

    public boolean updateOfficeResource(Long oldResourceId, OfficeResource newOfficeResource);

    public List<OfficeResource> getAllOfficeResources();

    public List<OfficeResource> getOfficeResourcesByType(OfficeResourceType officeResourceType);

    public List<OfficeResource> getOfficeResourcesByFloor(String floor);

    public List<OfficeResource> getOfficeResourcesByRoom(String room);
}
