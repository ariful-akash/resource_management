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

    @Autowired(required = true)
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

    /**
     *
     * @param accessType
     * @return
     */
    @Override
    public int updateAccessType(AccessType accessType) {

        if (accessType.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(accessType);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }
        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int updateAccessType(Long id) {

        AccessType accessType = getAccessType(id);
        if (accessType != null) {

            try {

                return updateAccessType(accessType);
            } catch (Exception e) {

                return 0;
            }
        }

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

    /**
     *
     * @param id
     * @return
     */
    @Override
    public AccessType getAccessType(Long id) {

        if (id > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (AccessType) session.get(AccessType.class, id);
        }

        return null;
    }

    /**
     * Read all the access types from "access_types" table
     *
     * @return
     */
    @Override
    public List<AccessType> getAllAccessTypes() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM AccessType";

        @SuppressWarnings("unchecked")
        List<AccessType> accessTypes = session.createQuery(hql).list();

        return accessTypes;
    }

    /**
     * Read list of access types from "access_type" table by an role id. It provides the default access type list for a specific Role.
     *
     * @param roleId
     * @return
     */
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
