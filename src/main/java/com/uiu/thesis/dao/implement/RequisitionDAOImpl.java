package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionDAO;
import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.models.requisition.Requisition;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.HumanResource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
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

    @Autowired
    private RequisitionTypeDAO requisitionTypeDAO;

    /**
     * Add new requisition
     *
     * @param requisition
     * @return
     */
    @Override
    public int addRequisition(Requisition requisition) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(requisition);
        return Integer.valueOf(id.toString());
    }

    /**
     * Returns all the requisitions
     *
     * @return
     */
    @Override
    public List<Requisition> getAllRequisitions() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Requisition.class);

        @SuppressWarnings("unchecked")
        List<Requisition> req = criteria.list();

        return req;
    }

    /**
     *
     * @param requisitionId
     * @return
     */
    @Override
    public Requisition getRequisitionById(Long requisitionId) {

        if (requisitionId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Requisition) session.get(Requisition.class, requisitionId);
        }

        return null;
    }

    /**
     * Returns the requisitions on the basis of solved or not
     *
     * @param solved
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsBySolved(boolean solved) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Requisition req WHERE req.solved = :solved";

        Query query = session.createQuery(hql);
        query.setParameter("solved", solved);

        @SuppressWarnings("unchecked")
        List<Requisition> requisitions = query.list();

        return requisitions;
    }

    /**
     * Returns the list of requisitions of a solver
     *
     * @param solverId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsBySolver(Long solverId) {

        if (solverId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Requisition req WHERE req.solverId = :solverId";

            Query query = session.createQuery(hql);
            query.setParameter("solverId", solverId);

            @SuppressWarnings("unchecked")
            List<Requisition> requisitions = query.list();

            return requisitions;
        }

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

    /**
     * Returns List of Requisitions of a creator
     *
     * @param creatorId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsByCreator(Long creatorId) {

        if (creatorId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Requisition req WHERE req.creatorId = :creatorId";

            Query query = session.createQuery(hql);
            query.setParameter("creatorId", creatorId);

            @SuppressWarnings("unchecked")
            List<Requisition> requisitions = query.list();

            return requisitions;
        }

        return null;
    }

    @Override
    public List<Requisition> getRequisitionsByType(RequisitionType requisitionType) {

        return null;
    }

    /**
     * Returns List of Requisitions of a type
     *
     * @param requisitionTypeId
     * @return
     */
    @Override
    public List<Requisition> getRequisitionsByType(Long requisitionTypeId) {

        if (requisitionTypeId > 0) {

            String sql = "select * from requisitions "
                    + "where type_id = " + requisitionTypeId;

            return getRequisitionsBySQL(sql);
        }

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

    /**
     *
     * @param sql
     * @return
     */
    private List<Requisition> getRequisitionsBySQL(String sql) {

        if (sql != null && !sql.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List resultList = query.list();

            List<Requisition> requisitions = new ArrayList<>();

            for (Object object : resultList) {

                Requisition requisition = new Requisition();

                Map row = (Map) object;

                BigInteger idInt = (BigInteger) row.get("id");

                requisition.setId(idInt.longValue());
                requisition.setSolved((boolean) row.get("solved"));

                idInt = (BigInteger) row.get("creator_id");
                requisition.setCreatorId(idInt.longValue());

                requisition.setPurpose((String) row.get("purpose"));
                requisition.setQuantity((int) row.get("quantity"));
                requisition.setRemarks((String) row.get("remarks"));

                requisition.setRequisitionNeedDate((Date) row.get("requisition_need_date"));
                requisition.setRequisitionPlacingDate((Date) row.get("requisition_placing_date"));
                requisition.setRequisitionSolvedDate((Date) row.get("requisition_solved_date"));

                idInt = (BigInteger) row.get("solver_id");
                if (idInt != null) {

                    requisition.setSolverId(idInt.longValue());
                }

                idInt = (BigInteger) row.get("type_id");
                requisition.setType(requisitionTypeDAO.getRequisitionTypeById(idInt.longValue()));

                requisitions.add(requisition);
            }

            return requisitions;
        }

        return null;
    }

}
