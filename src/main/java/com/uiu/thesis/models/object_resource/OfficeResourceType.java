package com.uiu.thesis.models.object_resource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "office_resource_types")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OfficeResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "resource_type", nullable = false, unique = true)
    private String resourceType;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<OfficeResource> officeResources;
    /**
     * Constructor
     */
    public OfficeResourceType() {
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

//    public List<OfficeResource> getOfficeResources() {
//        return officeResources;
//    }
//
//    public void setOfficeResources(List<OfficeResource> officeResources) {
//        this.officeResources = officeResources;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.resourceType);
//        hash = 37 * hash + Objects.hashCode(this.officeResources);
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
        final OfficeResourceType other = (OfficeResourceType) obj;
        if (!Objects.equals(this.resourceType, other.resourceType)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
//        if (!Objects.equals(this.officeResources, other.officeResources)) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "OfficeResourceType{" + "id=" + id + ", resourceType=" + resourceType + '}';
    }

}
