package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.requisition.Requisition;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RequisitionService {

    public int addRequisition(Requisition requisition);

    public int updateRequisition(Requisition requisition);

    public List<Requisition> getAllRequisitions();

    public List<Requisition> getRequisitionsBySolver(Long solverId);

    public List<Requisition> getRequisitionsByCreator(Long creatorId);

    public List<Requisition> getRequisitionsByType(Long typeId);

    public Requisition getRequisitionById(Long id);

    public List<Requisition> getRequisitionsBySU(boolean solved);

    public List<Requisition> getRequisitionsByDate(Date from, Date to);
}
