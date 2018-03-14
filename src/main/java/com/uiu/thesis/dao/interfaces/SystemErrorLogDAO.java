package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.logs.SystemErrorLog;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface SystemErrorLogDAO {

    public boolean addErrorLog(SystemErrorLog errorLog);

    public List<SystemErrorLog> getAllErrorLogs();

    public List<SystemErrorLog> getErrorLogsByDate(Date from);

    public List<SystemErrorLog> getErrorLogsByDate(Date from, Date to);

    public List<SystemErrorLog> getErrorLogsByException(String exceptionClass);

    public List<SystemErrorLog> getErrorLogsByUser(HumanResource user);

    public List<SystemErrorLog> getErrorLogsByUser(Long userId);
}
