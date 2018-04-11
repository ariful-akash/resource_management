package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.complaint.Complaint;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintService {

    public int addNewComplaint(Complaint complaint);

    public List<Complaint> getAllComplaints();

    public List<Complaint> getComplaintsByType(Long id);

    public Complaint getComplaintById(Long id);

    public List<Complaint> getComplaintsByCreator(Long id);

    public List<Complaint> getComplaintsBySolver(Long id);

    public List<Complaint> getComplaintsSU(boolean solved);

    public List<Complaint> getComplaintsByDate(Date from, Date to);
}
