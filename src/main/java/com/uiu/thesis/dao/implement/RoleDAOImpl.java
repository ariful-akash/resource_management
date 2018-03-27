package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
import java.util.List;
import javax.transaction.Transactional;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param role
     * @return
     */
    @Override
    public int addRole(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(role);
        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role) {

        Session session = sessionFactory.getCurrentSession();

        if (role != null && role.getId() != 0) {

            try {

                session.update(role);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    @Override
    public int editRoleDescription(Long roleId, String newDescription) {

        return 0;
    }

    @Override
    public int editRoleDescription(Role role, String newDescription) {

        return 0;
    }

    @Override
    public List<Role> getRolesByAccesType(AccessType accessType) {

        return null;
    }

    @Override
    public Role getRoleByUser(HumanResource user) {

        return null;
    }

    @Override
    public Role getRoleByUser(Long userId) {

        return null;
    }

    /**
     *
     * @param roleId
     * @return
     */
    @Override
    public Role getRoleById(Long roleId) {

        Session session = sessionFactory.getCurrentSession();
        Role role = (Role) session.get(Role.class, roleId);

        return role;
    }

    /**
     *
     *
     * @return
     */
    @Override
    public List<Role> getAllRoles() {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Role";

        @SuppressWarnings("unchecked")
        List<Role> roles = session.createQuery(hql).list();

        return roles;
    }

    /**
     *
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleByName(String roleName) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Role role WHERE role.role = :roleName";
        Query query = session.createQuery(hql);
        query.setParameter("roleName", roleName);

        return (Role) query.list().get(0);
    }

}
