/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.models.forum;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content", nullable = false)
    private String contet;

    @Column(name = "post_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResource poster;

    @ManyToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TagType> tagTypes;

    /**
     * Constructor
     */
    public Post() {
    }

}
