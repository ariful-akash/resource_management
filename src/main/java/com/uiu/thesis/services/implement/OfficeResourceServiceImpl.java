package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.services.interfaces.OfficeResourceService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashif
 */
@Service
@Transactional
public class OfficeResourceServiceImpl implements OfficeResourceService {

    @Autowired
    private OfficeResourceDAO officeResourceDAO;

    /**
     *
     * @param officeResource
     * @return
     */
    @Override
    public int addOfficeResource(OfficeResource officeResource) {

        if (officeResource != null
                && officeResource.getId() == null
                && officeResource.getFloor() != null
                && officeResource.getRoom() != null
                && officeResource.getQuantity() > 0
                && officeResource.getType() != null) {

            return officeResourceDAO.addOfficeResource(officeResource);
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<OfficeResource> getAllOfficeResources() {

        return officeResourceDAO.getAllOfficeResources();
    }

    /**
     *
     * @param typeId
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResourcesByType(Long typeId) {

        if (typeId > 0) {

            return officeResourceDAO.getOfficeResourcesByType(typeId);
        }

        return null;
    }

    /**
     *
     * @param room
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResourcesByRoom(String room) {

        if (room != null && !room.isEmpty()) {

            return officeResourceDAO.getOfficeResourcesByRoom(room);
        }

        return null;
    }

    /**
     *
     * @param floor
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResourcesByFloor(String floor) {

        if (floor != null && !floor.isEmpty()) {

            return officeResourceDAO.getOfficeResourcesByFloor(floor);
        }

        return null;
    }

}
