package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.OfficeResourceDAO;
import com.uiu.thesis.models.object_resource.OfficeResource;
import java.math.BigInteger;
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

    /**
     * Insert office resource or update the already exist
     *
     * @param officeResource
     * @return
     */
    @Override
    public int addOfficeResource(OfficeResource officeResource) {

        Session session = sessionFactory.getCurrentSession();

        String sql = "SELECT * FROM resource_management.office_resources"
                + " WHERE floor = '" + officeResource.getFloor() + "'"
                + " AND room = '" + officeResource.getRoom() + "'"
                + " AND type_id = " + officeResource.getType().getId();

        List<OfficeResource> officeResources = makeOfficeResourcesBySQL(sql);

        if (officeResources != null && officeResources.size() > 0) {

            OfficeResource currentResource = officeResources.get(0);

            currentResource.setQuantity(
                    currentResource.getQuantity()
                    + officeResource.getQuantity());

            currentResource.setType(officeResource.getType());

            int value = updateOfficeResource(currentResource);

            return value;

        } else if (officeResources == null
                && officeResource.getId() == null
                && officeResource.getType() != null) {

            Long id = (Long) session.save(officeResource);
            return Integer.valueOf(id.toString());
        }

        return 0;
    }

    /**
     * Update the office resource
     *
     * @param officeResource
     * @return
     */
    @Override
    public int updateOfficeResource(OfficeResource officeResource) {

        if (officeResource != null
                && officeResource.getId() != null
                && officeResource.getType() != null) {

            Session session = sessionFactory.getCurrentSession();

            try {

                session.update(officeResource);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
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

            String sql = "SELECT * FROM office_resources "
                    + "WHERE type_id = " + typeId;

            List<OfficeResource> officeResources = makeOfficeResourcesBySQL(sql);

            return officeResources;
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

        List results = query.list();

        if (results != null && results.size() > 0) {

            List<OfficeResource> officeResources = new ArrayList<>();

            for (Object result : results) {

                OfficeResource officeResource = new OfficeResource();

                Map row = (Map) result;

                BigInteger idInt = (BigInteger) row.get("id");

                officeResource.setId(idInt.longValue());
                officeResource.setFloor((String) row.get("floor"));
                officeResource.setRoom((String) row.get("room"));
                officeResource.setQuantity((int) row.get("quantity"));

                officeResources.add(officeResource);
            }

            return officeResources;
        }

        return null;
    }

    /**
     *
     * @param typeId
     * @param floor
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResources(Long typeId, String floor) {

        if (typeId != null && typeId > 0
                && floor != null && !floor.isEmpty()) {

            String sql = "SELECT * FROM office_resources"
                    + " WHERE type_id = " + typeId
                    + " AND floor = '" + floor + "'";

            return makeOfficeResourcesBySQL(sql);
        }

        return null;
    }

    /**
     *
     * @param typeId
     * @param floor
     * @param room
     * @return
     */
    @Override
    public List<OfficeResource> getOfficeResources(Long typeId, String floor, String room) {

        if (typeId != null && typeId > 0
                && floor != null && !floor.isEmpty()
                && room != null && !room.isEmpty()) {

            String sql = "SELECT * FROM office_resources"
                    + " WHERE type_id = " + typeId
                    + " AND floor = '" + floor + "'"
                    + " AND room = '" + room + "'";

            return makeOfficeResourcesBySQL(sql);
        }

        return null;
    }
}
