package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionDAO;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public class RequisitionDAOImpl implements RequisitionDAO {

    @Override
    public boolean addRequisition(Requisition requisition) {

        return false;
    }

    @Override
    public List<Requisition> getAllRequisitions() {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsBySolved(boolean solved) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsBySolver(Long solverId) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsBySolver(HumanResource solver) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsBySolver(HumanResource solver, boolean solved) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByCreator(HumanResource creator) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByCreator(HumanResource creator, boolean solved) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByCreator(Long creatorId) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByType(RequisitionType requisitionType) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByType(Long requisitionTypeId) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByDate(Date from) {

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByDate(Date from, Date to) {

        return null;
    }

}
