package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface RequisitionDAO {

    public boolean addRequisition(Requisition requisition);

    public List<Requisition> getAllRequisitions();

    public List<Requisition> getRequisitionsBySolved(boolean solved);

    public List<Requisition> getRequisitionsBySolver(Long solverId);

    public List<Requisition> getRequisitionsBySolver(HumanResource solver);

    public List<Requisition> getRequisitionsBySolver(HumanResource solver, boolean solved);

    public List<Requisition> getRequisitionsByCreator(HumanResource creator);

    public List<Requisition> getRequisitionsByCreator(HumanResource creator, boolean solved);

    public List<Requisition> getRequisitionsByCreator(Long creatorId);

    public List<Requisition> getRequisitionsByType(RequisitionType requisitionType);

    public List<Requisition> getRequisitionsByType(Long requisitionTypeId);

    public List<Requisition> getRequisitionsByDate(Date from);

    public List<Requisition> getRequisitionsByDate(Date from, Date to);
}
