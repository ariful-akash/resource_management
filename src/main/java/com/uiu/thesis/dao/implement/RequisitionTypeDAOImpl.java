package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.RequisitionTypeDAO;
import com.uiu.thesis.models.requisition.RequisitionType;
import com.uiu.thesis.models.user.AccessType;
import java.util.List;
import javax.transaction.Transactional;
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
     * @return
     */
    @Override
    public List<RequisitionType> getAllRequisitionTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(AccessType accessType) {

        return null;
    }

    @Override
    public RequisitionType getRequisitionTypeByAccessType(Long accessTypeId) {

        return null;
    }
}
