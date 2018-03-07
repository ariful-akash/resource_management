package com.uiu.thesis.models.logs;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "user_session_logs")
public class UserSessionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "user_agent_version")
    private String userAgentVersion;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "session_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date sessionDate;

    /**
     * Constructor
     */
    public UserSessionLog() {
    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgentVersion() {
        return userAgentVersion;
    }

    public void setUserAgentVersion(String userAgentVersion) {
        this.userAgentVersion = userAgentVersion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.userId);
        hash = 97 * hash + Objects.hashCode(this.userAgent);
        hash = 97 * hash + Objects.hashCode(this.userAgentVersion);
        hash = 97 * hash + Objects.hashCode(this.ipAddress);
        hash = 97 * hash + Objects.hashCode(this.sessionDate);
        return hash;
    }

    /**
     * Equals method
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserSessionLog other = (UserSessionLog) obj;
        if (!Objects.equals(this.userAgent, other.userAgent)) {
            return false;
        }
        if (!Objects.equals(this.userAgentVersion, other.userAgentVersion)) {
            return false;
        }
        if (!Objects.equals(this.ipAddress, other.ipAddress)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.sessionDate, other.sessionDate)) {
            return false;
        }
        return true;
    }

    /**
     * To string method
     *
     * @return
     */
    @Override
    public String toString() {
        return "UserSessionLog{" + "id=" + id + ", userId=" + userId + ", userAgent=" + userAgent + ", userAgentVersion=" + userAgentVersion + ", ipAddress=" + ipAddress + ", sessionDate=" + sessionDate + '}';
    }
}
