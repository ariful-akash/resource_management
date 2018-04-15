package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.object_resource.OfficeResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface OfficeResourceService {

    public int addOfficeResource(OfficeResource officeResource);

    public List<OfficeResource> getAllOfficeResources();

    public List<OfficeResource> getOfficeResourcesByType(Long typeId);

    public List<OfficeResource> getOfficeResourcesByRoom(String room);

    public List<OfficeResource> getOfficeResourcesByFloor(String floor);

}
