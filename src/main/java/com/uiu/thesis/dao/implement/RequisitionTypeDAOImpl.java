package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;

/**
 *
 * @author ashif
 */
public class RequisitionTypeDAOImpl implements RequisitionTypeDAO {

    @Override
    public boolean addRequisitionType(RequisitionType requisitionType) {

        return false;
    }

    @Override
    public RequisitionType getRequisitionTypeById(Long reqTypeId) {

        return null;
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(AccessType accessType) {

        return null;
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(Long accessTypeId) {

        return null;
    }

}
