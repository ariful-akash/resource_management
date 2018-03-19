package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
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
public class HumanResourceDAOImpl implements HumanResourceDAO {

    @Override
    public boolean addHumanResource(HumanResource hr) {

        return false;
    }

    @Override
    public boolean updateHumanResource(HumanResource oldHr, HumanResource newHr) {

        return false;
    }

    @Override
    public boolean deleteHumanResource(HumanResource hr) {

        return false;
    }

    @Override
    public HumanResource getHumanResource(Long id) {

        return null;
    }

    @Override
    public List<HumanResource> getAllHumanResources() {

        return null;
    }

    @Override
    public List<HumanResource> getHumanResources(HumanResourceType hrType) {

        return null;
    }

    @Override
    public List<HumanResource> getHumanResources(Role role) {

        return null;
    }

    @Override
    public List<HumanResource> getHumanResources(AccessType accessType) {

        return null;
    }

}
