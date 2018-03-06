package com.uiu.thesis.models.complain;

import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
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
}
