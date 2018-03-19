package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RoleDAO;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addRole(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(role);
        return Integer.valueOf(id.toString());
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

}
