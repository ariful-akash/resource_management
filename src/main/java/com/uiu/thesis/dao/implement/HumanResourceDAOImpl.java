package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.dao.interfaces.RoleDAO;
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

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccessTypeDAO accessTypeDAO;

    @Autowired
    private HumanResourceTypeDAO hrTypeDao;

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
            hr.setLastName((String) row.get("last_name"));
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

        if (id != null && id > 0) {

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

        if (hrId > 0 && roleId > 0) {

            HumanResource humanResource = getHumanResource(hrId);
            Role role = roleDAO.getRoleById(roleId);

            if (humanResource != null && role != null) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "UPDATE roles_human_resources "
                        + "SET roles_id=" + roleId + " "
                        + "WHERE humanResources_id=" + hrId;

                Query query = session.createSQLQuery(sql);
                int value = query.executeUpdate();
                humanResource.setAccess(role.getAccessTypes());

                return value;
            }
        }

        return 0;
    }

    /**
     *
     * @param hrId
     * @param roleId
     * @return
     */
    @Override
    public int mapHumanResourceRole(Long hrId, Long roleId) {

        if (hrId > 0 && roleId > 0) {

            HumanResource humanResource = getHumanResource(hrId);
            Role role = roleDAO.getRoleById(roleId);

            roleDAO.getRoleByUser(hrId);

            if (humanResource != null && role != null) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "INSERT INTO roles_human_resources "
                        + "(roles_id, humanResources_id) "
                        + "VALUES (" + roleId + ", " + hrId + ")";

                Query query = session.createSQLQuery(sql);
                int value = query.executeUpdate();
                humanResource.setAccess(role.getAccessTypes());

                return value;
            }
        }

        return 0;
    }

    /**
     *
     * @param hrId
     * @param accessId
     * @return
     */
    @Override
    public int addHumanResourceAccess(Long hrId, Long accessId) {

        if (hrId > 0 && accessId > 0) {

            HumanResource humanResource = getHumanResource(hrId);
            AccessType accessType = accessTypeDAO.getAccessType(accessId);

            if (humanResource != null && accessType != null) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "INSERT INTO human_resources_access_types "
                        + "(human_resources_id, access_id) "
                        + "VALUES (" + hrId + ", " + accessId + ")";

                Query query = session.createSQLQuery(sql);
                return query.executeUpdate();
            }
        }

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

        if (hrId > 0 && accessId > 0) {

            HumanResource humanResource = getHumanResource(hrId);
            AccessType accessType = accessTypeDAO.getAccessType(accessId);

            if (humanResource != null && accessType != null) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "DELETE FROM human_resources_access_types "
                        + "WHERE access_id=" + accessId + " "
                        + "AND human_resources_id=" + hrId;

                Query query = session.createSQLQuery(sql);
                return query.executeUpdate();
            }
        }

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

        if (hrId > 0 && typeId > 0) {

            HumanResource humanResource = getHumanResource(hrId);
            HumanResourceType hrType = hrTypeDao.getHumanResourceType(typeId);

            if (humanResource != null && hrType != null) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "UPDATE human_resource_types_human_resources "
                        + "SET human_resource_types_id=" + typeId + " "
                        + "WHERE humanResources_id=" + hrId;

                Query query = session.createSQLQuery(sql);
                return query.executeUpdate();
            }
        }

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

            HumanResourceType hrType = hrTypeDao.getHumanResourceType(hrTypeId);

            if (hrType != null) {

                Set<HumanResource> hrSet = hrType.getHumanResources();

                List<HumanResource> humanResourcesList = new ArrayList<>();

                for (HumanResource humanResource : hrSet) {

                    humanResourcesList.add(humanResource);
                }

                return humanResourcesList;
            }
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

            Role role = roleDAO.getRoleById(roleId);

            if (role != null) {

                Set<HumanResource> humanResourcesSet = role.getHumanResources();
                List<HumanResource> humanResourcesList = new ArrayList<>();

                for (HumanResource humanResource : humanResourcesSet) {

                    humanResourcesList.add(humanResource);
                }

                return humanResourcesList;
            }
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

    /**
     *
     * @param hrId
     * @param roleId
     * @return
     */
    @Override
    public boolean isRoleRelatesHR(Long hrId, Long roleId) {

        if (hrId > 0 && roleId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM roles_human_resources "
                    + "WHERE humanResources_id = " + hrId;
            Query query = session.createSQLQuery(sql);
            List result = query.list();

            if (result.size() > 0) {

                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param hrId
     * @param accessId
     * @return
     */
    @Override
    public boolean hasAccess(Long hrId, Long accessId) {

        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT * FROM human_resources_access_types"
                + " WHERE human_resources_id = " + hrId
                + " AND access_id = " + accessId;

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List result = query.list();

        return (result != null && result.size() > 0);
    }
}
