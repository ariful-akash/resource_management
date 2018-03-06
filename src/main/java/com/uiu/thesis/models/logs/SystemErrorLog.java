/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "system_error_logs")
public class SystemErrorLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exception_class")
    private String exceptionClass;

    @Column(name = "parent_class")
    private String parentClass;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "object_state")
    private String objectState;

    @Column(name = "error_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date errorDate;
}
