package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
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
public class AccessTypeDAOImpl implements AccessTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Insert a new Access Type object into "access_type" table of database
     *
     * @param accessType
     * @return
     */
    @Override
    public int addAccessType(AccessType accessType) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(accessType);
        return Integer.valueOf(id.toString());
    }

    @Override
    public int updateAccessType(AccessType accessType) {

        return 0;
    }

    @Override
    public int updateAccessType(Long id) {

        return 0;
    }

    @Override
    public int deleteAccessType(AccessType accessType) {

        return 0;
    }

    @Override
    public int deleteAccessType(Long id) {

        return 0;
    }

    @Override
    public List<AccessType> getAllAccessTypes() {

        return null;
    }

    @Override
    public List<AccessType> getAccessTypesByRole(Long roleId) {

        return null;
    }

    @Override
    public List<AccessType> getAccessTypesByRole(Role role) {

        return null;
    }

    @Override
    public List<AccessType> getAccessTypesByHR(Long hrId) {

        return null;
    }

    @Override
    public List<AccessType> getAccessTypesByHR(HumanResource hr) {

        return null;
    }

}
