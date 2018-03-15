package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public class ComplaintDAOImpl implements ComplaintDAO {

    @Override
    public boolean addComplaint(Complaint complaint) {

        return false;
    }

    @Override
    public boolean updateComplaint(Complaint oldComplaint, Complaint newComplaint) {

        return false;
    }

    @Override
    public boolean updateComplaint(Long complaintId, Complaint newComplaint) {

        return false;
    }

    @Override
    public List<Complaint> getComplaintsByType(ComplaintType complaintType) {

        return null;
    }

    @Override
    public List<Complaint> getComplaintsByComplainant(HumanResource complainant) {

        return null;
    }

    @Override
    public List<Complaint> getComplaintsByComplainant(Long complainantId) {

        return null;
    }

    @Override
    public List<Complaint> getComplaintsBySolver(HumanResource solver) {

        return null;
    }

    @Override
    public List<Complaint> getComplaintsBySolver(Long soverId) {

        return null;
    }

    @Override
    public Complaint getComplaint(Long complaintId) {

        return null;
    }

}
