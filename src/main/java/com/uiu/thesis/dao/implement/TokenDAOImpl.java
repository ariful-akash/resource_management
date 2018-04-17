package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.logs.SessionToken;
import com.uiu.thesis.models.user.HumanResource;
import com.uiu.thesis.util.RandomString;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
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

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean isTokenExist(String token) {

        long value = getUserId(token);
        if (value > 0) {

            return true;
        }

        return false;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public boolean isTokenExist(long userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM SessionToken st WHERE st.userId = :userId";

        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);

        @SuppressWarnings("unchecked")
        List<SessionToken> sessionTokens = query.list();

        if (sessionTokens != null && sessionTokens.size() > 0) {

            return true;
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
            if (!isTokenExist(userId)) {

                Session session = sessionFactory.getCurrentSession();
                Date date = new Date();

                SessionToken sessionToken = new SessionToken();
                sessionToken.setToken(token);
                sessionToken.setUserId(userId);
                sessionToken.setLoginTime(date);

                Long id = (Long) session.save(sessionToken);

                return Integer.valueOf(id.toString());
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

            Session session = sessionFactory.getCurrentSession();

            SessionToken sessionToken = getSessionToken(token);
            if (sessionToken != null) {

                session.delete(sessionToken);
                return 1;
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

            SessionToken sessionToken = getSessionToken(token);
            if (sessionToken != null) {

                return sessionToken.getUserId();
            }
        }

        return 0;
    }

    /**
     *
     * @param token
     * @return
     */
    public SessionToken getSessionToken(String token) {

        if (token != null && !token.isEmpty()) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM SessionToken st "
                    + "WHERE st.token = :token";

            Query query = session.createQuery(hql);
            query.setParameter("token", token);

            @SuppressWarnings("unchecked")
            List<SessionToken> tokens = query.list();

            if (tokens != null && !tokens.isEmpty()) {

                return tokens.get(0);
            }
        }

        return null;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public String getToken(String email, String password) {

        HumanResource user = humanResourceDAO.getHumanResource(email);
        if (user != null && password.equals(new String(user.getPassword()))) {

            RandomString randomString = new RandomString();
            String token = randomString.nextString();

            int value = addToken(token, user.getId());
            if (value != 0) {

                return token;
            } else {

                return "";
            }
        }

        return null;
    }
}
