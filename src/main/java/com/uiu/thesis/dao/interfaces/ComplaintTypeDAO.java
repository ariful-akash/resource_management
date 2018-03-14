package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.complaint.ComplaintType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintTypeDAO {

    public boolean addComplaintType(ComplaintType complaintType);

    public boolean updateComplaintType(ComplaintType oldComplaintType, ComplaintType newComplaintType);

    public boolean updateComplaintType(Long oldComplaintTypeId, ComplaintType newComplaintType);

    public List<ComplaintType> getComplaintTypes();
}
