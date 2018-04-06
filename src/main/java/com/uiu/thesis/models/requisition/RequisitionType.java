package com.uiu.thesis.models.requisition;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
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
@Table(name = "requisition_types")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RequisitionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Requisition> requisitions;

    /**
     * Constructor
     */
    public RequisitionType() {
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
    public Set<Requisition> getRequisitions() {
        return requisitions;
    }

    public void setRequisitions(Set<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.type);
//        hash = 97 * hash + Objects.hashCode(this.accessType);
        hash = 97 * hash + Objects.hashCode(this.requisitions);
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
        final RequisitionType other = (RequisitionType) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
//        if (!Objects.equals(this.accessType, other.accessType)) {
//            return false;
//        }
        if (!Objects.equals(this.requisitions, other.requisitions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequisitionType{" + "id=" + id + ", type=" + type + ", requisitions=" + requisitions + '}';
    }

}
