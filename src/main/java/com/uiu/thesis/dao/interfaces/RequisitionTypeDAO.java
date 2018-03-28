package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RequisitionTypeDAO {

    public int addRequisitionType(RequisitionType requisitionType);

    public RequisitionType getRequisitionTypeById(Long reqTypeId);

    public List<RequisitionType> getAllRequisitionTypes();

    public RequisitionType getRequisitionTypeByAccessType(AccessType accessType);

    public RequisitionType getRequisitionTypeByAccessType(Long accessTypeId);
}
