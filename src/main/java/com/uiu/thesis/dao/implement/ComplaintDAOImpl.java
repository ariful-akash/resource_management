package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.models.user.HumanResource;
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
public class ComplaintDAOImpl implements ComplaintDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param complaint
     * @return
     */
    @Override
    public int addComplaint(Complaint complaint) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(complaint);
        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param complaint
     * @return
     */
    @Override
    public int updateComplaint(Complaint complaint) {

        if (complaint.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(complaint);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    @Override
    public int deleteComplaint(Complaint complaint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteComplaint(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param complaintId
     * @return
     */
    @Override
    public Complaint getComplaint(Long complaintId) {

        if (complaintId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Complaint) session.get(Complaint.class, complaintId);
        }

        return null;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Complaint> getComplaintsByType(ComplaintType complaintType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Complaint> getComplaintsByComplainant(HumanResource complainant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Complaint> getComplaintsByComplainant(Long complainantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Complaint> getComplaintsBySolver(HumanResource solver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Complaint> getComplaintsBySolver(Long soverId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
