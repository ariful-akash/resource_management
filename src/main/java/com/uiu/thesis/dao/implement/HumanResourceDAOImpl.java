package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.models.user.AccessType;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.models.user.HumanResourceType;
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
