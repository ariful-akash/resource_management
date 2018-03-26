package com.uiu.thesis.models.user;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", length = 50)
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HumanResource> humanResources;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AccessType> accessTypes;

    /**
     * Constructor
     */
    public Role() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AccessType> getAccessTypes() {
        return accessTypes;
    }

    public void setAccessTypes(Set<AccessType> accessTypes) {
        this.accessTypes = accessTypes;
    }

    public List<HumanResource> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResource> humanResources) {
        this.humanResources = humanResources;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.role);
//        hash = 97 * hash + Objects.hashCode(this.accessTypes);
//        hash = 97 * hash + Objects.hashCode(this.humanResources);
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
        final Role other = (Role) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.accessTypes, other.accessTypes)) {
            return false;
        }
        if (!Objects.equals(this.humanResources, other.humanResources)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role=" + role + ", accessTypes=" + accessTypes + ", humanResources=" + humanResources + '}';
    }

}
