package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.DBAccessLogDAO;
import com.uiu.thesis.models.logs.DBAccessLog;
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
public class DBAccessLogDAOImpl implements DBAccessLogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param accessLog
     * @return
     */
    @Override
    public int addDBAccesslog(DBAccessLog accessLog) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(accessLog);

        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @return
     */
    @Override
    public List<DBAccessLog> getAllDBAccessLogs() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM DBAccessLog";

        @SuppressWarnings("unchecked")
        List<DBAccessLog> dbLogs = session.createQuery(hql).list();

        return dbLogs;
    }

    /**
     *
     * @param dbLogId
     * @return
     */
    @Override
    public DBAccessLog getDBAccessLog(Long dbLogId) {

        if (dbLogId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (DBAccessLog) session.get(DBAccessLog.class, dbLogId);
        }

        return null;
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByUser(HumanResource user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByUser(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByDate(Date from) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DBAccessLog> getDBAccessLogsByDate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
