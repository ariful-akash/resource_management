package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.services.interfaces.ComplaintService;
import java.util.Date;
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
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintDAO complaintDAO;

    /**
     *
     * @param complaint
     * @return
     */
    @Override
    public int addNewComplaint(Complaint complaint) {

        if (complaint != null
                && complaint.getId() == null
                && complaint.getComplaintPlacingDate() != null
                && complaint.getCreatorId() != null
                && complaint.getDescription() != null
                && complaint.getType() != null) {

            return complaintDAO.addComplaint(complaint);
        }
        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByType(Long id) {

        if (id > 0) {

            return complaintDAO.getComplaintsByType(id);
        }

        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByCreator(Long id) {

        if (id != null && id > 0) {

            return complaintDAO.getComplaintsByComplainant(id);
        }

        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Complaint> getComplaintsBySolver(Long id) {

        if (id != null && id > 0) {

            return complaintDAO.getComplaintsBySolver(id);
        }

        return null;
    }

    /**
     *
     * @param solved
     * @return
     */
    @Override
    public List<Complaint> getComplaintsSU(boolean solved) {

        return complaintDAO.getComplaintsBySU(solved);
    }

    @Override
    public List<Complaint> getComplaintsByDate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Complaint getComplaintById(Long id) {

        if (id > 0) {

            return complaintDAO.getComplaint(id);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Complaint> getAllComplaints() {

        return complaintDAO.getAllComplaints();
    }

}
