package com.uiu.thesis.models.requisition;

import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "requisitions")
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

    @Column(name = "purpose", length = 1000)
    private String purpose;

    @Column(name = "remarks", length = 1000)
    private String remarks;

    @Column(name = "solved")
    private boolean isSolved;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResource solver;

    @ManyToOne
    private HumanResource requisitionCreator;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "requisition_tag_types", joinColumns = {
        @JoinColumn(name = "requisition_id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id")})
    private List<TagType> tags;

    /**
     * Constructor
     */
    public Requisition() {
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

    public boolean isIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public HumanResource getSolver() {
        return solver;
    }

    public void setSolver(HumanResource solver) {
        this.solver = solver;
    }

    public HumanResource getRequisitionCreator() {
        return requisitionCreator;
    }

    public void setRequisitionCreator(HumanResource requisitionCreator) {
        this.requisitionCreator = requisitionCreator;
    }

    public List<TagType> getTags() {
        return tags;
    }

    public void setTags(List<TagType> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + this.quantity;
        hash = 43 * hash + Objects.hashCode(this.requisitionNeedDate);
        hash = 43 * hash + Objects.hashCode(this.requisitionPlacingDate);
        hash = 43 * hash + Objects.hashCode(this.purpose);
        hash = 43 * hash + Objects.hashCode(this.remarks);
        hash = 43 * hash + (this.isSolved ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.solver);
        hash = 43 * hash + Objects.hashCode(this.requisitionCreator);
        hash = 43 * hash + Objects.hashCode(this.tags);
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
        if (this.isSolved != other.isSolved) {
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
        if (!Objects.equals(this.solver, other.solver)) {
            return false;
        }
        if (!Objects.equals(this.requisitionCreator, other.requisitionCreator)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Requisition{" + "id=" + id + ", quantity=" + quantity + ", requisitionNeedDate=" + requisitionNeedDate + ", requisitionPlacingDate=" + requisitionPlacingDate + ", purpose=" + purpose + ", remarks=" + remarks + ", isSolved=" + isSolved + ", solver=" + solver + ", requisitionCreator=" + requisitionCreator + ", tags=" + tags + '}';
    }
}
