package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.TokenDAO;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Transactional
@Repository
public class TokenDAOImpl implements TokenDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param token
     * @param userId
     * @return
     */
    @Override
    public boolean isTokenExist(String token, long userId) {

        if (token != null && !token.isEmpty() && userId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM session_token "
                    + "WHERE token = " + token + " "
                    + "AND user_id = " + userId;

            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List result = query.list();

            if (result != null && result.size() > 0) {

                return true;
            }

        }

        return false;
    }

    /**
     *
     * @param token
     * @param userId
     * @return
     */
    @Override
    public int addToken(String token, long userId) {

        if (token != null && !token.isEmpty() && userId > 0) {

            /*
            Insert only if the token doesn't exist*/
            if (!isTokenExist(token, userId)) {

                Session session = sessionFactory.getCurrentSession();
                Date date = new Date();

                String sql = "INSERT INTO session_token "
                        + "(token, user_id, login_time) "
                        + "VALUES (" + token + ", " + userId + ", " + date + ")";

                Query query = session.createSQLQuery(sql);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

                return query.executeUpdate();
            }
        }

        return 0;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public int removeToken(String token) {

        if (token != null && !token.isEmpty()) {

            long userId = getUserId(token);
            if (userId > 0) {

                Session session = sessionFactory.getCurrentSession();
                String sql = "DELETE FROM session_token "
                        + "WHERE token = " + token;

                Query query = session.createSQLQuery(sql);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

                return query.executeUpdate();
            }
        }

        return 0;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public long getUserId(String token) {

        if (token != null && !token.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM session_token "
                    + "WHERE token = " + token;

            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List result = query.list();

            if (result != null && result.size() > 0) {

                Map row = (Map) result.get(0);
                BigInteger userId = (BigInteger) row.get("user_id");

                return userId.longValue();
            }
        }

        return 0;
    }

}
