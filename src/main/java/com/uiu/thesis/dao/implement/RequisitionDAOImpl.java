package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionDAO;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class RequisitionDAOImpl implements RequisitionDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public boolean addRequisition(Requisition requisition) {

        return false;
    }

    @Override
    public List<Requisition> getAllRequisitions() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Requisition.class);

        @SuppressWarnings("unchecked")
        List<Requisition> req = criteria.list();

        return req;
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
