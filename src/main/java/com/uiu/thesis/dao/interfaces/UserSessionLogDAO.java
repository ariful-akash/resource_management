package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.logs.UserSessionLog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface UserSessionLogDAO {

    public int addUserSession(UserSessionLog userSessionLog);

    public List<UserSessionLog> getAllUserSessions();

    public List<UserSessionLog> getUserSessions(Date from);

    public List<UserSessionLog> getUserSessions(Date from, Date to);

    public Long getTotalSession();
}
