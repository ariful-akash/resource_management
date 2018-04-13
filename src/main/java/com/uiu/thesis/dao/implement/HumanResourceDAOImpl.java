package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
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
     * @param sqlQuery
     * @return
     */
    private List<HumanResource> makeHRBySQLQuery(String sqlQuery) {

        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery(sqlQuery);
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

    /**
     *
     * @param hr
     * @return
     */
    @Override
    public int updateHumanResource(HumanResource hr) {

        if (hr != null && hr.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();

            try {

                session.update(hr);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

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

        if (id > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (HumanResource) session.get(HumanResource.class, id);
        }

        return null;
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
     * @param hrId
     * @param roleId
     * @return
     */
    @Override
    public int updateHumanResourceRole(Long hrId, Long roleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param hrId
     * @param accessId
     * @return
     */
    @Override
    public int addHumanResourceAccess(Long hrId, Long accessId) {

        return 0;
    }

    /**
     *
     * @param hrId
     * @param accessId
     * @return
     */
    @Override
    public int removeHumanResourceAccess(Long hrId, Long accessId) {

        return 0;
    }

    /**
     *
     * @param hrId
     * @param typeId
     * @return
     */
    @Override
    public int updateHumanResourceType(Long hrId, Long typeId) {

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

    /**
     *
     * @param hrTypeId
     * @return
     */
    @Override
    public List<HumanResource> getHumanResourcesByHRType(Long hrTypeId) {

        long id = hrTypeId;

        if (id > 0) {

            String sql = "select * from human_resources "
                    + "where id in ( "
                    + "select humanResources_id from human_resource_types_human_resources "
                    + "where human_resource_type_id = " + id + ")";

            return makeHRBySQLQuery(sql);
        }
        return null;
    }

    /**
     *
     * @param roleId
     * @return
     */
    @Override
    public List<HumanResource> getHumanResourcesByRole(Long roleId) {

        long id = roleId;

        if (id > 0) {

            String sql = "select * from human_resources "
                    + "where id in ( "
                    + "select humanResources_id from roles_human_resources "
                    + "where roles_id = " + id + ")";

            return makeHRBySQLQuery(sql);
        }

        return null;
    }

    /**
     *
     * @param accessTypeId
     * @return
     */
    @Override
    public List<HumanResource> getHumanResourcesByAccessType(Long accessTypeId) {

        long id = accessTypeId;

        if (id > 0) {

            String sql = "select * from human_resources "
                    + "where id in ( "
                    + "select human_resources_id from human_resources_access_types "
                    + "where access_id = " + id + ")";

            return makeHRBySQLQuery(sql);
        }

        return null;
    }
}
