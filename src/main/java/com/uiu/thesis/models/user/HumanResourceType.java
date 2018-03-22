package com.uiu.thesis.models.user;

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
@Table(name = "human_resource_types")
public class HumanResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "resource_name", nullable = false, unique = true)
    private String resourceName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "resourceType")
    private List<HumanResource> humanResources;

    /**
     * Constructor
     */
    public HumanResourceType() {
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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<HumanResource> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResource> humanResources) {
        this.humanResources = humanResources;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.resourceName);
        hash = 37 * hash + Objects.hashCode(this.humanResources);
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
        final HumanResourceType other = (HumanResourceType) obj;
        if (!Objects.equals(this.resourceName, other.resourceName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.humanResources, other.humanResources)) {
            return false;
        }
        return true;
    }

    /**
     * To string Method
     *
     * @return
     */
    @Override
    public String toString() {
        return "HumanResourceType{" + "id=" + id + ", resourceType=" + resourceName + ", humanResources=" + humanResources + '}';
    }

}
