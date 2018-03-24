package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.SystemErrorLogDAO;
import com.uiu.thesis.models.logs.SystemErrorLog;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
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
public class SystemErrorLogDAOImpl implements SystemErrorLogDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public boolean addErrorLog(SystemErrorLog errorLog) {

        return false;
    }

    /**
     * Read all the System Error Logs from "system_error_logs" table
     *
     * @return
     */
    @Override
    public List<SystemErrorLog> getAllErrorLogs() {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM SystemErrorLog";

        @SuppressWarnings("unchecked")
        List<SystemErrorLog> systemErrorLogs = session.createQuery(hql).list();

        return systemErrorLogs;
    }

    @Override
    public List<SystemErrorLog> getErrorLogsByDate(Date from) {

        return null;
    }

    @Override
    public List<SystemErrorLog> getErrorLogsByDate(Date from, Date to) {

        return null;
    }

    @Override
    public List<SystemErrorLog> getErrorLogsByException(String exceptionClass) {

        return null;
    }

    @Override
    public List<SystemErrorLog> getErrorLogsByUser(HumanResource user) {

        return null;
    }

    @Override
    public List<SystemErrorLog> getErrorLogsByUser(Long userId) {

        return null;
    }

}
