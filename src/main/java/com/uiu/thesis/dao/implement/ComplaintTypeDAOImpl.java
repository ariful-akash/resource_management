package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.ComplaintTypeDAO;
import com.uiu.thesis.models.complaint.ComplaintType;
import java.util.List;
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
public class ComplaintTypeDAOImpl implements ComplaintTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param complaintType
     * @return
     */
    @Override
    public int addComplaintType(ComplaintType complaintType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(complaintType);
        return Integer.valueOf(ret.toString());
    }

    /**
     *
     * @param complaintType
     * @return
     */
    @Override
    public int updateComplaintType(ComplaintType complaintType) {

        if (complaintType != null && complaintType.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(complaintType);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     * Read all the complaint types from "complaint_types" table
     *
     * @return
     */
    @Override
    public List<ComplaintType> getComplaintTypes() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ComplaintType.class);

        @SuppressWarnings("unchecked")
        List<ComplaintType> complaintTypes = criteria.list();

        return complaintTypes;
    }

    @Override
    public int deleteComplaintType(ComplaintType complaintType) {

        return 0;
    }

    @Override
    public int deleteComplaintType(Long id) {

        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ComplaintType getComplaintTypeById(Long id) {

        if (id > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (ComplaintType) session.get(ComplaintType.class, id);
        }

        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public ComplaintType getComplaintTypeByName(String name) {

        if (name != null && !name.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM ComplaintType ct WHERE ct.type = :type";

            Query query = session.createQuery(hql);
            query.setParameter("type", name);

            @SuppressWarnings("unchecked")
            List<ComplaintType> list = query.list();

            if (list != null && list.size() > 0) {

                return list.get(0);
            }
        }

        return null;
    }

}
