/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.models.forum;

import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "comment_reply")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reply", nullable = false)
    private String reply;

    @Column(name = "reply_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HumanResource replier;

    @ManyToOne(cascade = CascadeType.ALL)
    private Comment comment;

    /**
     * Constructor
     */
    public CommentReply() {
    }
}
