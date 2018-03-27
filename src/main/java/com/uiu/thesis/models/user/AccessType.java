package com.uiu.thesis.models.user;

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
@Table(name = "access_types")
public class AccessType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

//    @ManyToMany(mappedBy = "access", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<HumanResource> users;
//
//    @ManyToMany(mappedBy = "accessTypes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Role> roles;
    /**
     * Constructor
     */
    public AccessType() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<HumanResource> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<HumanResource> users) {
//        this.users = users;
//    }
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.description);
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
        final AccessType other = (AccessType) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccessType{" + "id=" + id + ", description=" + description + '}';
    }

}
