/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    /**
     * Constructor
     */
    public SystemErrorLog() {
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

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getObjectState() {
        return objectState;
    }

    public void setObjectState(String objectState) {
        this.objectState = objectState;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    /**
     * Hash Method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.userId);
        hash = 17 * hash + Objects.hashCode(this.exceptionClass);
        hash = 17 * hash + Objects.hashCode(this.parentClass);
        hash = 17 * hash + Objects.hashCode(this.errorMessage);
        hash = 17 * hash + Objects.hashCode(this.objectState);
        hash = 17 * hash + Objects.hashCode(this.errorDate);
        return hash;
    }

    /**
     * Equals Method
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
        final SystemErrorLog other = (SystemErrorLog) obj;
        if (!Objects.equals(this.exceptionClass, other.exceptionClass)) {
            return false;
        }
        if (!Objects.equals(this.parentClass, other.parentClass)) {
            return false;
        }
        if (!Objects.equals(this.errorMessage, other.errorMessage)) {
            return false;
        }
        if (!Objects.equals(this.objectState, other.objectState)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.errorDate, other.errorDate)) {
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
        return "SystemErrorLog{" + "id=" + id + ", userId=" + userId + ", exceptionClass=" + exceptionClass + ", parentClass=" + parentClass + ", errorMessage=" + errorMessage + ", objectState=" + objectState + ", errorDate=" + errorDate + '}';
    }

}
