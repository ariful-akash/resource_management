package com.uiu.thesis.models.user;

import com.uiu.thesis.models.complain.Complaint;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.requisition.Requisition;
import java.io.Serializable;
import java.util.List;
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

    @Column(name = "designation", nullable = false)
    private String designation;

    @Lob
    @Column(name = "iamge", nullable = true)
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResourceType resourceType;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_modified_access", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "access_id")})
    private List<AccessType> accessTypes;

    @OneToMany(mappedBy = "requisitionCreator", cascade = CascadeType.ALL)
    private List<Requisition> requisitions;

    @OneToMany(mappedBy = "complainant", cascade = CascadeType.ALL)
    private List<Complaint> complaints;

    /**
     * Constructor
     */
    public HumanResource() {
    }
}
