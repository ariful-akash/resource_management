package com.uiu.thesis.models.requisition;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@Table(name = "requisitions")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Requisition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "requisition_need_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requisitionNeedDate;

    @Column(name = "requisition_placing_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requisitionPlacingDate;

    @Column(name = "requisition_solved_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requisitionSolvedDate;

    @Column(name = "purpose", length = 1000)
    private String purpose;

    @Column(name = "remarks", length = 3000, nullable = true)
    private String remarks;

    @Column(name = "solved")
    private boolean solved = false;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "solver_id")
    private Long solverId;

    /**
     * Constructor
     */
    public Requisition() {
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

    public Date getRequisitionNeedDate() {
        return requisitionNeedDate;
    }

    public void setRequisitionNeedDate(Date requisitionNeedDate) {
        this.requisitionNeedDate = requisitionNeedDate;
    }

    public Date getRequisitionPlacingDate() {
        return requisitionPlacingDate;
    }

    public void setRequisitionPlacingDate(Date requisitionPlacingDate) {
        this.requisitionPlacingDate = requisitionPlacingDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean isSolved) {
        this.solved = isSolved;
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

    public Date getRequisitionSolvedDate() {
        return requisitionSolvedDate;
    }

    public void setRequisitionSolvedDate(Date requisitionSovedDate) {
        this.requisitionSolvedDate = requisitionSovedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + this.quantity;
        hash = 61 * hash + Objects.hashCode(this.requisitionNeedDate);
        hash = 61 * hash + Objects.hashCode(this.requisitionPlacingDate);
        hash = 61 * hash + Objects.hashCode(this.requisitionSolvedDate);
        hash = 61 * hash + Objects.hashCode(this.purpose);
        hash = 61 * hash + Objects.hashCode(this.remarks);
        hash = 61 * hash + Objects.hashCode(this.creatorId);
        hash = 61 * hash + Objects.hashCode(this.solverId);
        hash = 61 * hash + (this.solved ? 1 : 0);

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
        final Requisition other = (Requisition) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.solved != other.solved) {
            return false;
        }
        if (!Objects.equals(this.purpose, other.purpose)) {
            return false;
        }
        if (!Objects.equals(this.remarks, other.remarks)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.requisitionNeedDate, other.requisitionNeedDate)) {
            return false;
        }
        if (!Objects.equals(this.requisitionPlacingDate, other.requisitionPlacingDate)) {
            return false;
        }
        if (!Objects.equals(this.requisitionSolvedDate, other.requisitionSolvedDate)) {
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

    @Override
    public String toString() {
        return "Requisition{" + "id=" + id + ", quantity=" + quantity + ", requisitionNeedDate=" + requisitionNeedDate + ", requisitionPlacingDate=" + requisitionNeedDate + ", requisitionSolvedDate=" + requisitionSolvedDate + ", purpose=" + purpose + ", remarks=" + remarks + ", isSolved=" + solved + '}';
    }

}
