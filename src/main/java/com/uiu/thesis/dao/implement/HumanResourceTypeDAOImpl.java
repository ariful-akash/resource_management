package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceTypeDAO;
import com.uiu.thesis.models.user.HumanResourceType;
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
public class HumanResourceTypeDAOImpl implements HumanResourceTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     * Insert an human resource type object into database
     *
     * @param hrType
     * @return
     */
    @Override
    public int addHRType(HumanResourceType hrType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(hrType);
        return Integer.valueOf(ret.toString());
    }

    @Override
    public boolean updateHRType(HumanResourceType hrType) {

        return false;
    }

    @Override
    public List<HumanResourceType> getAllHRType() {

        return null;
    }

}
