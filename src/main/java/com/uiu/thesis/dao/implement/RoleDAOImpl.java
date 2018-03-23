package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RoleDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired
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
        session.update(role);
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
        Criteria criteria = session.createCriteria(Role.class);

        @SuppressWarnings("unchecked")
        List<Role> roles = criteria.list();

        return roles;
    }

}
