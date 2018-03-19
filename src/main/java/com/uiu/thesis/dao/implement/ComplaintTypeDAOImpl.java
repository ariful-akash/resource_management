package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class ComplaintTypeDAOImpl implements ComplaintTypeDAO {

    @Override
    public boolean addComplaintType(ComplaintType complaintType) {

        return false;
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
