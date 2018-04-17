package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.models.object_resource.OfficeResource;
import com.uiu.thesis.models.user.HumanResourceType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
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

    @Autowired
    private HumanResourceTypeDAO humanResourceTypeDAO;

    /**
     *
     * @param officeResource
     * @return
     */
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

    /**
     * Read all the Office Resource from "office_resource" table
     *
     * @return
     */
    @Override
    public List<OfficeResource> getAllOfficeResources() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OfficeResource.class);

        @SuppressWarnings("unchecked")
        List<OfficeResource> or = criteria.list();

        return or;
    }

    /**
     *
     * @param typeId
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResourcesByType(Long typeId) {

        if (typeId > 0) {

            HumanResourceType hrType = humanResourceTypeDAO.getHumanResourceType(typeId);
            if (hrType != null) {

                String sql = "SELECT * FROM office_resources "
                        + "WHERE type_id = " + typeId;

                List<OfficeResource> officeResources = makeOfficeResourcesBySQL(sql);

                return officeResources;
            }
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

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM OfficeResource "
                    + "WHERE floor = :floor";

            Query query = session.createQuery(hql);
            query.setParameter("floor", floor);

            @SuppressWarnings("unchecked")
            List<OfficeResource> officeResources = query.list();

            return officeResources;
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

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM OfficeResource "
                    + "WHERE room = :room";

            Query query = session.createQuery(hql);
            query.setParameter("room", room);

            @SuppressWarnings("unchecked")
            List<OfficeResource> officeResources = query.list();

            return officeResources;
        }

        return null;
    }

    /**
     *
     * @param sql
     * @return
     */
    private List<OfficeResource> makeOfficeResourcesBySQL(String sql) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List result = query.list();

        List<OfficeResource> officeResources = new ArrayList<>();

        for (Object object : result) {

            Map row = (Map) object;
        }

        return null;
    }
}
