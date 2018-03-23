package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceTypeDAO;
import com.uiu.thesis.models.object_resource.OfficeResourceType;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
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
public class OfficeResourceTypeDAOImpl implements OfficeResourceTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     * Insert an office resource type object into database
     *
     * @param
     * @return
     */
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

    /**
     * Read an object of OfficeResourceType from database by the id(primary key)
     *
     * @param
     * @return
     */
    @Override
    public OfficeResourceType getOfficeResourceType(Long typeId) {

        Session session = sessionFactory.getCurrentSession();
        return (OfficeResourceType) session.get(OfficeResourceType.class, typeId);
    }

    /**
     * Read all the Office Resource types from "office_resource_types" table
     *
     * @return
     */
    @Override
    public List<OfficeResourceType> getAllOfficeResourceTypes() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OfficeResourceType.class);

        @SuppressWarnings("unchecked")
        List<OfficeResourceType> orType = criteria.list();

        return orType;
    }

}
