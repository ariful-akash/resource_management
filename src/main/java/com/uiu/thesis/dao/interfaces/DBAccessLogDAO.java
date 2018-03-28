package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.logs.DBAccessLog;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface DBAccessLogDAO {

    public int addDBAccesslog(DBAccessLog accessLog);

    public List<DBAccessLog> getAllDBAccessLogs();

    public DBAccessLog getDBAccessLog(Long dbLogId);

    public List<DBAccessLog> getDBAccessLogsByUser(HumanResource user);

    public List<DBAccessLog> getDBAccessLogsByUser(Long userId);

    public List<DBAccessLog> getDBAccessLogsByDate(Date from);

    public List<DBAccessLog> getDBAccessLogsByDate(Date from, Date to);

}
