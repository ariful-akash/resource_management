package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.SystemErrorLogDAO;
import com.uiu.thesis.models.logs.SystemErrorLog;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public class SystemErrorLogDAOImpl implements SystemErrorLogDAO {

    @Override
    public boolean addErrorLog(SystemErrorLog errorLog) {

        return false;
    }

    @Override
    public List<SystemErrorLog> getAllErrorLogs() {

        return null;
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
