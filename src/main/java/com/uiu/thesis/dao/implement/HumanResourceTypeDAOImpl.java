package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.models.user.HumanResourceType;
import java.math.BigInteger;
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
public class HumanResourceTypeDAOImpl implements HumanResourceTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     * Insert an human resource type object into database
     *
     * @param hrType
     * @return
     */
    @Override
    public int addHRType(HumanResourceType hrType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(hrType);
        return Integer.valueOf(ret.toString());
    }

    @Override
    public int updateHRType(HumanResourceType hrType) {

        Session session = sessionFactory.getCurrentSession();

        if (hrType != null && hrType.getId() != 0) {

            try {

                session.update(hrType);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<HumanResourceType> getAllHRType() {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM HumanResourceType";

        @SuppressWarnings("unchecked")
        List<HumanResourceType> hrTypes = session.createQuery(hql).list();

        return hrTypes;
    }

    /**
     * Read an object of HumanResourceType from database by the id(primary key)
     *
     * @param hrTypeId
     * @return
     */
    @Override
    public HumanResourceType getHumanResourceType(Long hrTypeId) {

        Session session = sessionFactory.getCurrentSession();
        return (HumanResourceType) session.get(HumanResourceType.class, hrTypeId);
    }

    /**
     *
     * @param resourceName
     * @return
     */
    @Override
    public HumanResourceType getHumanResourceType(String resourceName) {

        if (resourceName != null) {

            Session session = sessionFactory.getCurrentSession();
            HumanResourceType hrt = null;

            String hql = "FROM HumanResourceType hrt WHERE hrt.resourceName = :resourceName";
            Query query = session.createQuery(hql);
            query.setParameter("resourceName", resourceName);

            List resultList = query.list();

            if (!resultList.isEmpty()) {

                hrt = (HumanResourceType) resultList.get(0);
            }

            return hrt;
        }

        return null;
    }

    /**
     *
     * @param hrId
     * @return
     */
    @Override
    public HumanResourceType getHRTypeByHRId(Long hrId) {

        if (hrId != null && hrId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM human_resource_types_human_resources "
                    + "WHERE humanResources_id = " + hrId;

            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            List result = query.list();
            if (result.size() > 0) {

                Map row = (Map) result.get(0);
                BigInteger idInt = (BigInteger) row.get("human_resource_types_id");

                HumanResourceType hrType = getHumanResourceType(idInt.longValue());

                return hrType;
            }
        }

        return null;
    }
}
