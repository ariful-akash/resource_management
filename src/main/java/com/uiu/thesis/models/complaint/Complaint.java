package com.uiu.thesis.models.complaint;

import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "remarks", length = 1000, nullable = true)
    private String remarks;

    @Column(name = "solved", nullable = false)
    private boolean isSolved;

    @Column(name = "feedback", nullable = true)
    private String feedback;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "complaint_type_id")
    private ComplaintType complaintType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "complaint_creator_id")
    private HumanResource complaintCreator;

    @ManyToOne(cascade = CascadeType.ALL)
    private HumanResource solver;

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

    public ComplaintType getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(ComplaintType complaintType) {
        this.complaintType = complaintType;
    }

    public HumanResource getComplaintCreator() {
        return complaintCreator;
    }

    public void setComplaintCreator(HumanResource complaintCreator) {
        this.complaintCreator = complaintCreator;
    }

    public HumanResource getSolver() {
        return solver;
    }

    public void setSolver(HumanResource solver) {
        this.solver = solver;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.complaintPlacingDate);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.remarks);
        hash = 67 * hash + (this.isSolved ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.complaintType);
        hash = 67 * hash + Objects.hashCode(this.complaintCreator);
        hash = 67 * hash + Objects.hashCode(this.solver);
        hash = 67 * hash + Objects.hashCode(this.feedback);
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
        if (!Objects.equals(this.complaintType, other.complaintType)) {
            return false;
        }
        if (!Objects.equals(this.complaintCreator, other.complaintCreator)) {
            return false;
        }
        if (!Objects.equals(this.solver, other.solver)) {
            return false;
        }
        if (!Objects.equals(this.feedback, other.feedback)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", complaintPlacingDate=" + complaintPlacingDate + ", description=" + description + ", remarks=" + remarks + ", isSolved=" + isSolved + ", feedback=" + feedback + ", complaintType=" + complaintType + ", complaintCreator=" + complaintCreator + ", solver=" + solver + '}';
    }

}
