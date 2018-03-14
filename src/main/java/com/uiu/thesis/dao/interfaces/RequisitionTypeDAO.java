package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;

/**
 *
 * @author ashif
 */
public interface RequisitionTypeDAO {

    public boolean addRequisitionType(RequisitionType requisitionType);

    public RequisitionType getRequisitionTypeById(Long reqTypeId);

    public RequisitionType getRequisitionTypeByAccessType(AccessType accessType);

    public RequisitionType getRequisitionTypeByAccessType(Long accessTypeId);
}
