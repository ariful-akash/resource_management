package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.DBAccessLogDAO;
import com.uiu.thesis.models.logs.DBAccessLog;
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
public class DBAccessLogDAOImpl implements DBAccessLogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addDBAccesslog(DBAccessLog accessLog) {

        return false;
    }

    /**
     * Read all the DBaccess Log from "db_access_logs" table
     *
     * @return
     */
    @Override
    public List<DBAccessLog> getAllDBAccessLogs() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(DBAccessLog.class);

        @SuppressWarnings("unchecked")
        List<DBAccessLog> dBAccessLogs = criteria.list();

        return dBAccessLogs;
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByUser(HumanResource user) {

        return null;
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByUser(Long userId) {

        return null;
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByDate(Date from) {

        return null;
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByDate(Date from, Date to) {

        return null;
    }

    @Override
    public DBAccessLog getDBAccessLog(Long dbLogId) {

        return null;
    }

}
