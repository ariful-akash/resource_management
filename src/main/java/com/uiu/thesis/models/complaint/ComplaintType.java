package com.uiu.thesis.models.complaint;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "complaint_types")
public class ComplaintType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Complaint> complaints;

    /**
     * Constructor
     */
    public ComplaintType() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public AccessType getAccessType() {
//        return accessType;
//    }
//
//    public void setAccessType(AccessType accessType) {
//        this.accessType = accessType;
//    }
//
    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.type);
//        hash = 29 * hash + Objects.hashCode(this.accessType);
//        hash = 29 * hash + Objects.hashCode(this.complaints);
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
        final ComplaintType other = (ComplaintType) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
//        if (!Objects.equals(this.accessType, other.accessType)) {
//            return false;
//        }
        if (!Objects.equals(this.complaints, other.complaints)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComplaintType{" + "id=" + id + ", type=" + type + ", complaints=" + complaints + '}';
    }

}
