package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class OfficeResourceTypeDAOImpl implements OfficeResourceTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addOfficeResourceType(OfficeResourceType officeResourceType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(officeResourceType);

        return Integer.valueOf(ret.toString());
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
