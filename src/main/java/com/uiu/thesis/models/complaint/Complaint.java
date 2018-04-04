package com.uiu.thesis.models.complaint;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "complaints")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "complaint_placing_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date complaintPlacingDate;

    @Column(name = "complaint_solved_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date complaintSolvedDate;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "remarks", length = 3000, nullable = true)
    private String remarks;

    @Column(name = "solved", nullable = false)
    private boolean isSolved = false;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "solver_id")
    private Long solverId;

    /**
     * Constructor
     */
    public Complaint() {
    }

    /**
     * Getter and setter
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getComplaintPlacingDate() {
        return complaintPlacingDate;
    }

    public void setComplaintPlacingDate(Date complaintPlacingDate) {
        this.complaintPlacingDate = complaintPlacingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public Date getComplaintSolvedDate() {
        return complaintSolvedDate;
    }

    public void setComplaintSolvedDate(Date complaintSolvedDate) {
        this.complaintSolvedDate = complaintSolvedDate;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getSolverId() {
        return solverId;
    }

    public void setSolverId(Long solverId) {
        this.solverId = solverId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.complaintPlacingDate);
        hash = 67 * hash + Objects.hashCode(this.complaintSolvedDate);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.remarks);
        hash = 67 * hash + Objects.hashCode(this.solverId);
        hash = 67 * hash + Objects.hashCode(this.creatorId);
        hash = 67 * hash + (this.isSolved ? 1 : 0);
        return hash;
    }

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
        final Complaint other = (Complaint) obj;
        if (this.isSolved != other.isSolved) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.remarks, other.remarks)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.complaintPlacingDate, other.complaintPlacingDate)) {
            return false;
        }
        if (!Objects.equals(this.complaintSolvedDate, other.complaintSolvedDate)) {
            return false;
        }
        if (!Objects.equals(this.creatorId, other.creatorId)) {
            return false;
        }
        if (!Objects.equals(this.solverId, other.solverId)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", complaintPlacingDate=" + complaintPlacingDate + ", description=" + description + ", remarks=" + remarks + ", isSolved=" + isSolved + '}';
    }

}
