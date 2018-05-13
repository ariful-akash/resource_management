package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.services.interfaces.RequisitionTypeService;
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
public class RequisitionTypeServiceImpl implements RequisitionTypeService {

    @Autowired
    private RequisitionTypeDAO requisitionTypeDAO;

    /**
     * Add requisition type
     *
     * @param requisitionType
     * @return
     */
    @Override
    public int addRequisitionType(RequisitionType requisitionType) {

        if (requisitionType != null
                && requisitionType.getId() == null
                && requisitionType.getType() != null
                && !requisitionType.getType().isEmpty()) {

            requisitionType.setType(requisitionType.getType().toLowerCase());

            RequisitionType dbRequisitionType
                    = requisitionTypeDAO.getRequisitionTypeByName(requisitionType.getType());

            if (dbRequisitionType == null) {

                return requisitionTypeDAO.addRequisitionType(requisitionType);
            }
        }

        return 0;
    }

    /**
     * Returns all the requisitions
     *
     * @return
     */
    @Override
    public List<RequisitionType> getAllRequisitionTypes() {

        return requisitionTypeDAO.getAllRequisitionTypes();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public RequisitionType getRequisitionType(Long id) {

        if (id > 0) {

            return requisitionTypeDAO.getRequisitionTypeById(id);
        }

        return null;
    }

}
