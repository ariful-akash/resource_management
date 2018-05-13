package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.complaint.ComplaintType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface ComplaintTypeDAO {

    public int addComplaintType(ComplaintType complaintType);

    public int updateComplaintType(ComplaintType complaintType);

    public int deleteComplaintType(ComplaintType complaintType);

    public int deleteComplaintType(Long id);

    public ComplaintType getComplaintTypeById(Long id);

    public ComplaintType getComplaintTypeByName(String name);

    public List<ComplaintType> getComplaintTypes();
}
