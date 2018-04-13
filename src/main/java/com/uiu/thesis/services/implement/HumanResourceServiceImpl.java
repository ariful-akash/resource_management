package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.services.interfaces.HumanResourceService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashif
 */
@Service
@Transactional
public class HumanResourceServiceImpl implements HumanResourceService {

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    /**
     *
     * @return
     */
    @Override
    public List<HumanResource> getHumanResources() {

        return humanResourceDAO.getAllHumanResources();
    }

    /**
     *
     * @param typeId
     * @return
     */
    @Override
    public List<HumanResource> getHumanResourcesByType(Long typeId) {

        if (typeId > 0) {

            return humanResourceDAO.getHumanResourcesByHRType(typeId);
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

        if (roleId > 0) {

            return humanResourceDAO.getHumanResourcesByRole(roleId);
        }

        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public HumanResource getHumanResourceById(Long id) {

        if (id > 0) {

            return humanResourceDAO.getHumanResource(id);
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
    public int changeHumanResourceRole(Long hrId, Long roleId) {

        if (hrId > 0 && roleId > 0) {

            return humanResourceDAO.updateHumanResourceRole(hrId, roleId);
        }

        return 0;
    }

    /**
     *
     * @param humanResource
     * @return
     */
    @Override
    public int addHumanResource(HumanResource humanResource) {

        if (humanResource != null
                && humanResource.getId() == null
                && humanResource.getFirstName() != null
                && humanResource.getEmail() != null
                && humanResource.getPassword() != null) {

            return humanResourceDAO.addHumanResource(humanResource);
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
    public int addAccess(Long hrId, Long accessId) {

        if (hrId > 0 && accessId > 0) {

            return humanResourceDAO.addHumanResourceAccess(hrId, accessId);
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
    public int removeAccess(Long hrId, Long accessId) {

        if (hrId > 0 && accessId > 0) {

            return humanResourceDAO.removeHumanResourceAccess(hrId, accessId);
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
    public int changeHumanResourceType(Long hrId, Long typeId) {

        if (hrId > 0 && typeId > 0) {

            return humanResourceDAO.updateHumanResourceType(hrId, typeId);
        }

        return 0;
    }

}
