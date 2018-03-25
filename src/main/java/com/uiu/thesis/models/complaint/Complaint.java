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

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "complaint_placing_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date complaintPlacingDate;

    @Column(name = "complaint_solved_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date complaintSolvedDate;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "remarks", length = 1000, nullable = true)
    private String remarks;

    @Column(name = "solved", nullable = false)
    private boolean isSolved;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + this.quantity;
        hash = 67 * hash + Objects.hashCode(this.complaintPlacingDate);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.remarks);
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
        if (this.quantity != other.quantity) {
            return false;
        }
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
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", quantity=" + quantity + ", complaintPlacingDate=" + complaintPlacingDate + ", description=" + description + ", remarks=" + remarks + ", isSolved=" + isSolved + '}';
    }

}
