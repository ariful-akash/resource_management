/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.models.forum;

import com.uiu.thesis.models.user.HumanResource;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public HumanResource getReplier() {
        return replier;
    }

    public void setReplier(HumanResource replier) {
        this.replier = replier;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.reply);
        hash = 17 * hash + Objects.hashCode(this.dateTime);
        hash = 17 * hash + Objects.hashCode(this.replier);
        hash = 17 * hash + Objects.hashCode(this.comment);
        return hash;
    }

    /**
     * equals method
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
        final CommentReply other = (CommentReply) obj;
        if (!Objects.equals(this.reply, other.reply)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        if (!Objects.equals(this.replier, other.replier)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentReply{" + "id=" + id + ", reply=" + reply + ", dateTime=" + dateTime + ", replier=" + replier + ", comment=" + comment + '}';
    }
}
