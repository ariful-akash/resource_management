package com.uiu.thesis.models.complain;

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
import javax.persistence.GenerationType;
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
@Table(name = "complaint_master")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "complaint_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date complainDate;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "solved")
    private boolean isSolved;

    @Column(name = "remarks", length = 1500)
    private String remarks;

    @ManyToOne
    private HumanResource complainant;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "complaints_tag_types", joinColumns = {
        @JoinColumn(name = "complaint_id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id")})
    private List<TagType> tags;

    /**
     * Constructor
     */
    public Complaint() {
    }

    /**
     * Getter Setter
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public HumanResource getComplainant() {
        return complainant;
    }

    public void setComplainant(HumanResource complainant) {
        this.complainant = complainant;
    }

    public List<TagType> getTags() {
        return tags;
    }

    public void setTags(List<TagType> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.complainDate);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + (this.isSolved ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.remarks);
        hash = 37 * hash + Objects.hashCode(this.complainant);
        hash = 37 * hash + Objects.hashCode(this.tags);
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
        if (!Objects.equals(this.complainDate, other.complainDate)) {
            return false;
        }
        if (!Objects.equals(this.complainant, other.complainant)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", complainDate=" + complainDate + ", description=" + description + ", isSolved=" + isSolved + ", remarks=" + remarks + ", complainant=" + complainant.toString() + ", tags=" + tags + '}';
    }

}
