package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
import com.uiu.thesis.models.user.Role;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
public class HumanResourceDAOImpl implements HumanResourceDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param hr
     * @return
     */
    @Override
    public int addHumanResource(HumanResource hr) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(hr);
        return Integer.valueOf(id.toString());
    }

    @Override
    public int updateHumanResource(HumanResource oldHr, HumanResource newHr) {

        return 0;
    }

    @Override
    public int deleteHumanResource(HumanResource hr) {

        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public HumanResource getHumanResource(Long id) {

        Session session = sessionFactory.getCurrentSession();
        return (HumanResource) session.get(HumanResource.class, id);
    }

    /**
     *
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<HumanResource> getAllHumanResources() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM HumanResource";
        Query query = session.createQuery(hql);

        return query.list();
    }

    /**
     *
     * @param hrType
     * @return
     */
    @Override
    public List<HumanResource> getHumanResources(HumanResourceType hrType) {

        return null;
    }

    /**
     *
     * @param role
     * @return
     */
    @Override
    public List<HumanResource> getHumanResources(Role role) {

        return null;
    }

    /**
     *
     * @param accessTypeId
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<HumanResource> getHumanResources(Long accessTypeId) {

        long id = accessTypeId;

        if (id > 0) {

            Session session = sessionFactory.getCurrentSession();

            String sql = "select * from human_resources "
                    + "where id in ( "
                    + "select human_resources_id from human_resources_access_types "
                    + "where access_id = " + id + ")";

            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List results = query.list();

            List<HumanResource> humanResources = new ArrayList<>();

            for (Object result : results) {

                HumanResource hr = new HumanResource();
                Map row = (Map) result;

                BigInteger idInt = (BigInteger) row.get("id");
                long idlong = idInt.longValue();

                hr.setId(idlong);
                hr.setFirstName((String) row.get("first_name"));
                hr.setLastName((String) row.get("name_name"));
                hr.setEmail((String) row.get("email"));
                hr.setPassword((byte[]) row.get("password"));
                hr.setDepartment((String) row.get("department"));
                hr.setPhone((String) row.get("phone"));
                hr.setImage((byte[]) row.get("image"));

                humanResources.add(hr);
            }

            return humanResources;
        }

        return null;
    }

    /**
     *
     * @param hr
     * @param role
     * @return
     */
    @Override
    public int updateHumanResource(HumanResource hr, Role role) {

        return 0;
    }

    /**
     *
     * @param hr
     * @param hrType
     * @return
     */
    @Override
    public int updateHumanResource(HumanResource hr, HumanResourceType hrType) {

        return 0;
    }

    /**
     *
     * @param hr
     * @param accessTypes
     * @return
     */
    @Override
    public int updateHumanResource(HumanResource hr, Set<AccessType> accessTypes) {

        Session session = sessionFactory.getCurrentSession();

        if (!accessTypes.isEmpty()) {

            Set<AccessType> accessSet = new HashSet<>();

            for (AccessType accessType : accessSet) {

                accessSet.add(accessType);
            }

            hr.setAccess(accessSet);
            try {

                session.update(hr);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public HumanResource getHumanResource(String email) {

        Session session = sessionFactory.getCurrentSession();
        HumanResource humanResource = null;

        String hql = "FROM HumanResource hr WHERE hr.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);

        List resultList = query.list();

        if (resultList != null) {

            humanResource = (HumanResource) resultList.get(0);
        }

        return humanResource;
    }

}
