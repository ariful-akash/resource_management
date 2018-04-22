package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.RequisitionDAO;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.services.interfaces.RequisitionService;
import java.util.Date;
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
public class RequisitionServiceImpl implements RequisitionService {

    @Autowired
    private RequisitionDAO requisitionDAO;

    /**
     * Add a new requisition
     *
     * @param requisition
     * @return
     */
    @Override
    public int addRequisition(Requisition requisition) {

        if (requisition != null
                && requisition.getId() == null
                && requisition.getCreatorId() != null
                && requisition.getSolverId() == null
                && requisition.isSolved() == false
                && requisition.getPurpose() != null
                && !requisition.getPurpose().isEmpty()
                && requisition.getQuantity() > 0
                && requisition.getRequisitionPlacingDate() != null
                && requisition.getRequisitionSolvedDate() == null
                && requisition.getType() != null
                && !requisition.getType().getType().isEmpty()) {

            return requisitionDAO.addRequisition(requisition);
        }

        return 0;
    }

    /**
     * Returns all the requisitions
     *
     * @return
     */
    @Override
    public List<Requisition> getAllRequisitions() {

        return requisitionDAO.getAllRequisitions();
    }

    /**
     *
     * @param solverId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsBySolver(Long solverId) {

        if (solverId > 0) {

            return requisitionDAO.getRequisitionsBySolver(solverId);
        }
        return null;
    }

    /**
     *
     * @param creatorId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsByCreator(Long creatorId) {

        if (creatorId > 0) {

            return requisitionDAO.getRequisitionsByCreator(creatorId);
        }

        return null;
    }

    /**
     *
     * @param typeId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsByType(Long typeId) {

        if (typeId > 0) {

            return requisitionDAO.getRequisitionsByType(typeId);
        }

        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Requisition getRequisitionById(Long id) {

        if (id > 0) {

            return requisitionDAO.getRequisitionById(id);
        }

        return null;
    }

    /**
     *
     * @param solved
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsBySU(boolean solved) {

        return requisitionDAO.getRequisitionsBySolved(solved);
    }

    @Override
    public List<Requisition> getRequisitionsByDate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param requisition
     * @return
     */
    @Override
    public int updateRequisition(Requisition requisition) {

        if (requisition != null && requisition.getId() > 0) {

            return requisitionDAO.updateRequisition(requisition);
        }

        return 0;
    }

}
