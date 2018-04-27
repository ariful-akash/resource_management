package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.object_resource.OfficeResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface OfficeResourceDAO {

    public int addOfficeResource(OfficeResource officeResource);

    public int updateOfficeResource(OfficeResource officeResource);

    public boolean updateOfficeResource(Long oldResourceId, OfficeResource newOfficeResource);

    public List<OfficeResource> getAllOfficeResources();

    public List<OfficeResource> getOfficeResourcesByType(Long typeId);

    public List<OfficeResource> getOfficeResourcesByFloor(String floor);

    public List<OfficeResource> getOfficeResourcesByRoom(String roomId);
}
