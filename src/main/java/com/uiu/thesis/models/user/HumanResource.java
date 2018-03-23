package com.uiu.thesis.models.user;

import com.uiu.thesis.models.complaint.Complaint;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.requisition.Requisition;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @Column(name = "department", nullable = false)
    private String department;

    @Lob
    @Column(name = "iamge", nullable = true)
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResourceType resourceType;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> posts;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_modified_access", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "access_id")})
    private Set<AccessType> accessTypes;

    @OneToMany(mappedBy = "requisitionCreator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Requisition> requisitions;

    @OneToMany(mappedBy = "complaintCreator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Complaint> complaints;

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

    public HumanResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(HumanResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<AccessType> getAccessTypes() {
        return accessTypes;
    }

    public void setAccessTypes(Set<AccessType> accessTypes) {
        this.accessTypes = accessTypes;
    }

    public Set<Requisition> getRequisitions() {
        return requisitions;
    }

    public void setRequisitions(Set<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.firstName);
        hash = 19 * hash + Objects.hashCode(this.lastName);
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Arrays.hashCode(this.password);
        hash = 19 * hash + Objects.hashCode(this.phone);
        hash = 19 * hash + Objects.hashCode(this.department);
        hash = 19 * hash + Arrays.hashCode(this.image);
        hash = 19 * hash + Objects.hashCode(this.resourceType);
        hash = 19 * hash + Objects.hashCode(this.posts);
        hash = 19 * hash + Objects.hashCode(this.comments);
        hash = 19 * hash + Objects.hashCode(this.role);
        hash = 19 * hash + Objects.hashCode(this.accessTypes);
        hash = 19 * hash + Objects.hashCode(this.requisitions);
        hash = 19 * hash + Objects.hashCode(this.complaints);
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
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.resourceType, other.resourceType)) {
            return false;
        }
        if (!Objects.equals(this.posts, other.posts)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.accessTypes, other.accessTypes)) {
            return false;
        }
        if (!Objects.equals(this.requisitions, other.requisitions)) {
            return false;
        }
        if (!Objects.equals(this.complaints, other.complaints)) {
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
        return "HumanResource{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phone=" + phone + ", department=" + department + ", image=" + image + ", resourceType=" + resourceType + ", posts=" + posts + ", comments=" + comments + ", role=" + role + ", accessTypes=" + accessTypes + ", requisitions=" + requisitions + ", complaints=" + complaints + '}';
    }
}
