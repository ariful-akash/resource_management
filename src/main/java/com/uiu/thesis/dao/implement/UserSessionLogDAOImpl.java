package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.UserSessionLogDAO;
import com.uiu.thesis.models.logs.UserSessionLog;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class UserSessionLogDAOImpl implements UserSessionLogDAO {

    @Override
    public boolean addUserSession(UserSessionLog userSessionLog) {

        return false;
    }

    @Override
    public List<UserSessionLog> getAllUserSessions() {

        return null;
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
