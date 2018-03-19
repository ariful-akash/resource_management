package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.AccessTypeDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.Role;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class AccessTypeDAOImpl implements AccessTypeDAO {

    @Override
    public boolean addAccessType(AccessType accessType) {

        return false;
    }

    @Override
    public boolean updateAccessType(AccessType accessType) {

        return false;
    }

    @Override
    public boolean updateAccessType(Long id) {

        return false;
    }

    @Override
    public boolean deleteAccessType(AccessType accessType) {

        return false;
    }

    @Override
    public boolean deleteAccessType(Long id) {

        return false;
    }

    @Override
    public List<AccessType> getAllAccessTypes() {

        return null;
    }

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
