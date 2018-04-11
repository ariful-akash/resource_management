package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintDAO {

    public int addComplaint(Complaint complaint);

    public int updateComplaint(Complaint complaint);

    public int deleteComplaint(Complaint complaint);

    public int deleteComplaint(Long id);

    public Complaint getComplaint(Long complaintId);

    public List<Complaint> getAllComplaints();

    public List<Complaint> getComplaintsByType(Long typeId);

    public List<Complaint> getComplaintsByComplainant(HumanResource complainant);

    public List<Complaint> getComplaintsByComplainant(Long complainantId);

    public List<Complaint> getComplaintsBySolver(HumanResource solver);

    public List<Complaint> getComplaintsBySolver(Long soverId);
}
