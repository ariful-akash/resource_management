package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.requisition.RequisitionType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RequisitionTypeService {

    public int addRequisitionType(RequisitionType requisitionType);

    public List<RequisitionType> getAllRequisitionTypes();

    public RequisitionType getRequisitionType(Long id);
}
