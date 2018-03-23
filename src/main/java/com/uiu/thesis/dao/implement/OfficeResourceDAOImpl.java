package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class OfficeResourceDAOImpl implements OfficeResourceDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public int addOfficeResource(OfficeResource officeResource) {
        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(officeResource);

        return Integer.valueOf(ret.toString());
    }

    @Override
    public boolean updateOfficeResource(OfficeResource oldOfficeResource, OfficeResource newOfficeResource) {

        return false;
    }

    @Override
    public boolean updateOfficeResource(Long oldResourceId, OfficeResource newOfficeResource) {

        return false;
    }

    @Override
    public List<OfficeResource> getAllOfficeResources() {

        return null;
    }

    @Override
    public List<OfficeResource> getOfficeResourcesByType(OfficeResourceType officeResourceType) {

        return null;
    }

    @Override
    public List<OfficeResource> getOfficeResourcesByFloor(String floor) {

        return null;
    }

    @Override
    public List<OfficeResource> getOfficeResourcesByRoom(String room) {

        return null;
    }

}
