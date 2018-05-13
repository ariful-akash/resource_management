package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.UserSessionLogDAO;
import com.uiu.thesis.models.logs.UserSessionLog;
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
public class UserSessionLogDAOImpl implements UserSessionLogDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param userSessionLog
     * @return
     */
    @Override
    public int addUserSession(UserSessionLog userSessionLog) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(userSessionLog);

        return Integer.valueOf(id.toString());
    }

    /**
     * Read all the user session from "user_session_logs" table
     *
     * @return
     */
    @Override
    public List<UserSessionLog> getAllUserSessions() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(UserSessionLog.class);

        @SuppressWarnings("unchecked")
        List<UserSessionLog> sessionLogs = criteria.list();

        return sessionLogs;
    }

    @Override
    public List<UserSessionLog> getUserSessions(Date from) {

        return null;
    }

    @Override
    public List<UserSessionLog> getUserSessions(Date from, Date to) {

        return null;
    }

    @Override
    public Long getTotalSession() {

        return null;
    }

}
