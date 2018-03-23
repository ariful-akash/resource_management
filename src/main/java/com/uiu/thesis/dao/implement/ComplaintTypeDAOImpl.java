package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
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
public class ComplaintTypeDAOImpl implements ComplaintTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public int addComplaintType(ComplaintType complaintType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(complaintType);
        return Integer.valueOf(ret.toString());
    }

    @Override
    public boolean updateComplaintType(ComplaintType oldComplaintType, ComplaintType newComplaintType) {

        return false;
    }

    @Override
    public boolean updateComplaintType(Long oldComplaintTypeId, ComplaintType newComplaintType) {

        return false;
    }

    @Override
    public List<ComplaintType> getComplaintTypes() {

        return null;
    }

}
