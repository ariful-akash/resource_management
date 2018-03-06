package com.uiu.thesis.models.logs;

import java.io.Serializable;
import java.util.Date;
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
}
