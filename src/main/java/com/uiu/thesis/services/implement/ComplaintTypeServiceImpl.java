package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
import com.uiu.thesis.services.interfaces.ComplaintTypeService;
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
public class ComplaintTypeServiceImpl implements ComplaintTypeService {

    @Autowired
    private ComplaintTypeDAO complaintTypeDAO;

    /**
     *
     * @return
     */
    @Override
    public List<ComplaintType> getAllComplaintTypes() {

        return complaintTypeDAO.getComplaintTypes();
    }

    /**
     *
     * @param complaintType
     * @return
     */
    @Override
    public int addComplaint(ComplaintType complaintType) {

        if (complaintType != null
                && complaintType.getId() == null
                && complaintType.getType() != null
                && !complaintType.getType().isEmpty()) {

            ComplaintType dbComplaintType
                    = complaintTypeDAO.getComplaintTypeByName(complaintType.getType());
            if (dbComplaintType == null) {

                return complaintTypeDAO.addComplaintType(complaintType);
            }
        }

        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ComplaintType getComplaintType(Long id) {

        if (id > 0) {

            return complaintTypeDAO.getComplaintTypeById(id);
        }

        return null;
    }

}
