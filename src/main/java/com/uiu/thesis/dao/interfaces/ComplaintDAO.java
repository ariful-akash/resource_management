package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintDAO {

    public boolean addComplaint(Complaint complaint);

    public boolean updateComplaint(Complaint oldComplaint, Complaint newComplaint);

    public boolean updateComplaint(Long complaintId, Complaint newComplaint);

    public List<Complaint> getComplaintsByType(ComplaintType complaintType);

    public List<Complaint> getComplaintsByComplainant(HumanResource complainant);

    public List<Complaint> getComplaintsByComplainant(Long complainantId);

    public List<Complaint> getComplaintsBySolver(HumanResource solver);

    public List<Complaint> getComplaintsBySolver(Long soverId);

    public Complaint getComplaint(Long complaintId);
}
