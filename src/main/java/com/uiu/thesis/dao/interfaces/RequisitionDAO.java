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

    public int addRequisition(Requisition requisition);

    public int updateRequisition(Requisition requisition);

    public List<Requisition> getAllRequisitions();

    public Requisition getRequisitionById(Long requisitionId);

    public List<String> getYears();

    public List<Requisition> getRequisitionsBySolved(boolean solved);

    public List<Requisition> getRequisitionsBySolver(Long solverId);

    public List<Requisition> getRequisitionsBySolver(HumanResource solver);

    public List<Requisition> getRequisitionsBySolver(Long solverId, boolean solved);

    public List<Requisition> getRequisitionsByCreator(HumanResource creator);

    public List<Requisition> getRequisitionsByCreator(Long creatorId, boolean solved);

    public List<Requisition> getRequisitionsByCreator(Long creatorId);

    public List<Requisition> getRequisitionsByType(RequisitionType requisitionType);

    public List<Requisition> getRequisitionsByType(Long requisitionTypeId);

    public List<Requisition> getRequisitionsByType(Long requisitionTypeId, boolean solved);

    public List<Requisition> getRequisitionsByTypeDate(Long requisitionTypeId, String fromDate, String toDate, boolean solved);

    public List<Requisition> getRequisitionsByDate(Date from);

    public List<Requisition> getRequisitionsByDate(Date from, Date to);
}
