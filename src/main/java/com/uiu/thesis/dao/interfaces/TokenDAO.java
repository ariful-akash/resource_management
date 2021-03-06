package com.uiu.thesis.dao.interfaces;

/**
 *
 * @author ashif
 */
public interface TokenDAO {

    public boolean isTokenExist(String token);

    public boolean isTokenExist(long userId);

    public int addToken(String token, long userId);

    public int removeToken(String token);

    public long getUserId(String token);

    public String getToken(String email, String password);
}
