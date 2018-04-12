package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
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
public class RequisitionTypeDAOImpl implements RequisitionTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param requisitionType
     * @return
     */
    @Override
    public int addRequisitionType(RequisitionType requisitionType) {

        Session session = sessionFactory.getCurrentSession();
        Long ret = (Long) session.save(requisitionType);

        return Integer.valueOf(ret.toString());
    }

    /**
     *
     * @param reqTypeId
     * @return
     */
    @Override
    public RequisitionType getRequisitionTypeById(Long reqTypeId) {

        if (reqTypeId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (RequisitionType) session.get(RequisitionType.class, reqTypeId);
        }

        return null;
    }

    /**
     *
     * @param typeName
     * @return
     */
    @Override
    public RequisitionType getRequisitionTypeByName(String typeName) {

        if (typeName != null && !typeName.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM RequisitionType rt WHERE rt.type = :type";

            Query query = session.createQuery(hql);
            query.setParameter("type", typeName);

            @SuppressWarnings("unchecked")
            List<RequisitionType> requisitionTypes = query.list();

            if (requisitionTypes != null && requisitionTypes.size() > 0) {

                return requisitionTypes.get(0);
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<RequisitionType> getAllRequisitionTypes() {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM RequisitionType";

        @SuppressWarnings("unchecked")
        List<RequisitionType> requisitionTypes = session.createQuery(hql).list();

        return requisitionTypes;
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(AccessType accessType) {

        return null;
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(Long accessTypeId) {

        return null;
    }

    /**
     *
     * @param requisitionType
     * @return
     */
    @Override
    public int updateRequisitionType(RequisitionType requisitionType) {

        if (requisitionType != null && requisitionType.getId() > 0) {

            try {

                Session session = sessionFactory.getCurrentSession();
                session.update(requisitionType);
                return 1;
            } catch (HibernateException e) {

                return 0;
            }
        }

        return 0;
    }

}
