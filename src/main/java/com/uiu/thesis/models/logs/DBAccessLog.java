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
@Table(name = "db_access_logs")
public class DBAccessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "db_table_name")
    private String dbTableName;

    @Column(name = "change_type")
    private String changeType;

    @Column(name = "old_row_data", nullable = true)
    private String oldRowData;

    @Column(name = "new_row_data", nullable = false)
    private String newRowData;

    @Column(name = "change_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date changeDate;

    /**
     * Constructor
     */
    public DBAccessLog() {
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

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getOldRowData() {
        return oldRowData;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return newRowData;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.userId);
        hash = 67 * hash + Objects.hashCode(this.dbTableName);
        hash = 67 * hash + Objects.hashCode(this.changeType);
        hash = 67 * hash + Objects.hashCode(this.oldRowData);
        hash = 67 * hash + Objects.hashCode(this.newRowData);
        hash = 67 * hash + Objects.hashCode(this.changeDate);
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
        final DBAccessLog other = (DBAccessLog) obj;
        if (!Objects.equals(this.dbTableName, other.dbTableName)) {
            return false;
        }
        if (!Objects.equals(this.changeType, other.changeType)) {
            return false;
        }
        if (!Objects.equals(this.oldRowData, other.oldRowData)) {
            return false;
        }
        if (!Objects.equals(this.newRowData, other.newRowData)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.changeDate, other.changeDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBAccessLog{" + "id=" + id + ", userId=" + userId + ", dbTableName=" + dbTableName + ", changeType=" + changeType + ", oldRowData=" + oldRowData + ", newRowData=" + newRowData + ", changeDate=" + changeDate + '}';
    }
}
