package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.complaint.ComplaintType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintTypeService {

    public List<ComplaintType> getAllComplaintTypes();

    public int addComplaint(ComplaintType complaintType);

    public ComplaintType getComplaintType(Long id);
}
