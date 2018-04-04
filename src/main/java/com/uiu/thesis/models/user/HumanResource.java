package com.uiu.thesis.models.user;

import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.requisition.Requisition;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "human_resources")
public class HumanResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Lob
    @Column(name = "password", nullable = false)
    private byte[] password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "department", nullable = true)
    private String department;

    @Lob
    @Column(name = "iamge", nullable = true)
    private byte[] image;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AccessType> access;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Requisition> solvedRequisitions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Requisition> createdRequisitions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Complaint> solvedComplaints;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Complaint> createdComplaints;

    /**
     * Constructor
     */
    public HumanResource() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<AccessType> getAccess() {
        return access;
    }

    public void setAccess(Set<AccessType> access) {

        Set<AccessType> accessTypes = new HashSet<>();
        for (AccessType acces : access) {

            accessTypes.add(acces);
        }

        this.access = accessTypes;
    }

    public Set<Requisition> getSolvedRequisitions() {
        return solvedRequisitions;
    }

    public void setSolvedRequisitions(Set<Requisition> solvedRequisitions) {
        this.solvedRequisitions = solvedRequisitions;
    }

    public Set<Requisition> getCreatedRequisitions() {
        return createdRequisitions;
    }

    public void setCreatedRequisitions(Set<Requisition> createdRequisitions) {
        this.createdRequisitions = createdRequisitions;
    }

    public Set<Complaint> getSolvedComplaints() {
        return solvedComplaints;
    }

    public void setSolvedComplaints(Set<Complaint> solvedComplaints) {
        this.solvedComplaints = solvedComplaints;
    }

    public Set<Complaint> getCreatedComplaints() {
        return createdComplaints;
    }

    public void setCreatedComplaints(Set<Complaint> createdComplaints) {
        this.createdComplaints = createdComplaints;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.firstName);
        hash = 83 * hash + Objects.hashCode(this.lastName);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Arrays.hashCode(this.password);
        hash = 83 * hash + Objects.hashCode(this.phone);
        hash = 83 * hash + Objects.hashCode(this.department);
        return hash;
    }

    /**
     * Equals Method
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
        final HumanResource other = (HumanResource) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Arrays.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    /**
     * To String method
     *
     * @return
     */
    @Override
    public String toString() {
        return "HumanResource{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phone=" + phone + ", department=" + department + '}';
    }

}
