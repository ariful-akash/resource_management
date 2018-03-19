package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.DBAccessLogDAO;
import com.uiu.thesis.models.logs.DBAccessLog;
import com.uiu.thesis.models.user.HumanResource;
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
public class DBAccessLogDAOImpl implements DBAccessLogDAO {

    @Override
    public boolean addDBAccesslog(DBAccessLog accessLog) {

        return false;
    }

    @Override
    public List<DBAccessLog> getAllDBAccessLogs() {

        return null;
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
