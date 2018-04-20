package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintDAO;
import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.models.complaint.Complaint;
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
public class ComplaintDAOImpl implements ComplaintDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Autowired
    private ComplaintTypeDAO complaintTypeDAO;

    /**
     *
     * @param complaint
     * @return
     */
    @Override
    public int addComplaint(Complaint complaint) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(complaint);
        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param complaint
     * @return
     */
    @Override
    public int updateComplaint(Complaint complaint) {

        if (complaint != null && complaint.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(complaint);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    @Override
    public int deleteComplaint(Complaint complaint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteComplaint(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param complaintId
     * @return
     */
    @Override
    public Complaint getComplaint(Long complaintId) {

        if (complaintId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Complaint) session.get(Complaint.class, complaintId);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Complaint> getAllComplaints() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Complaint com ORDER BY com.complaintPlacingDate DESC";

        @SuppressWarnings("unchecked")
        List<Complaint> complaints = session.createQuery(hql).list();

        return complaints;
    }

    /**
     *
     * @param typeId
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByType(Long typeId) {

        if (typeId > 0) {

            String sql = "select * from complaints "
                    + "where type_id = " + typeId;

            return getComplaintsBySQL(sql);
        }
        return null;
    }

    /**
     *
     * @param typeId
     * @param solved
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByType(Long typeId, boolean solved) {

        if (typeId > 0) {

            String sql = "SELECT * from complaints"
                    + " WHERE type_id = " + typeId
                    + " AND solved = " + solved;

            return getComplaintsBySQL(sql);
        }
        return null;
    }

    /**
     *
     * @param creatorId
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByCreator(Long creatorId) {

        if (creatorId != null && creatorId > 0) {

            String sql = "select * from complaints "
                    + "where creator_id = " + creatorId;

            return getComplaintsBySQL(sql);
        }

        return null;
    }

    @Override
    public List<Complaint> getComplaintsBySolver(HumanResource solver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param solverId
     * @return
     */
    @Override
    public List<Complaint> getComplaintsBySolver(Long solverId) {

        if (solverId != null && solverId > 0) {

            String sql = "select * from complaints "
                    + "where solver_id = " + solverId;

            return getComplaintsBySQL(sql);
        }

        return null;
    }

    /**
     *
     * @param solved
     * @return
     */
    @Override
    public List<Complaint> getComplaintsBySU(boolean solved) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Complaint cmp WHERE cmp.isSolved = :solved";

        Query query = session.createQuery(hql);
        query.setParameter("solved", solved);

        @SuppressWarnings("unchecked")
        List<Complaint> complaints = query.list();

        return complaints;
    }

    /**
     *
     * @param sql
     * @return
     */
    private List<Complaint> getComplaintsBySQL(String sql) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List resultList = query.list();

        List<Complaint> complaints = new ArrayList<>();

        for (Object object : resultList) {

            Complaint complaint = new Complaint();

            Map row = (Map) object;

            BigInteger idInt = (BigInteger) row.get("id");

            complaint.setId(idInt.longValue());
            complaint.setComplaintPlacingDate((Date) row.get("complaint_placing_date"));
            complaint.setComplaintSolvedDate((Date) row.get("complaint_solved_date"));

            idInt = (BigInteger) row.get("creator_id");
            complaint.setCreatorId(idInt.longValue());

            complaint.setDescription((String) row.get("description"));
            complaint.setIsSolved((boolean) row.get("solved"));
            complaint.setRemarks((String) row.get("remarks"));

            idInt = (BigInteger) row.get("solver_id");
            if (idInt != null) {

                complaint.setSolverId(idInt.longValue());
            }

            idInt = (BigInteger) row.get("type_id");
            complaint.setType(complaintTypeDAO.getComplaintTypeById(idInt.longValue()));

            complaints.add(complaint);
        }

        return complaints;
    }

    /**
     *
     * @param creatorId
     * @param solved
     * @return
     */
    @Override
    public List<Complaint> getComplaintsByCreator(Long creatorId, boolean solved) {

        if (creatorId != null && creatorId > 0) {

            String sql = "SELECT * from complaints"
                    + " WHERE creator_id = " + creatorId
                    + " AND solved = " + solved;

            return getComplaintsBySQL(sql);
        }

        return null;
    }
}
