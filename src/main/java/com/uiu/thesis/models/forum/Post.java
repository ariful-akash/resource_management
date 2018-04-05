package com.uiu.thesis.models.forum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @Column(name = "content", nullable = false, length = 2000)
    private String contet;

    @Column(name = "post_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TagType> tags;

    @Column(name = "poster_id")
    private Long posterId;

    /**
     * Constructor
     */
    public Post() {
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

    public String getContet() {
        return contet;
    }

    public void setContet(String contet) {
        this.contet = contet;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

//    public HumanResource getPoster() {
//        return poster;
//    }
//
//    public void setPoster(HumanResource poster) {
//        this.poster = poster;
//    }
    public List<TagType> getTags() {
        return tags;
    }

    public void setTags(List<TagType> tags) {
        this.tags = tags;
    }

    /**
     * Hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.contet);
        hash = 97 * hash + Objects.hashCode(this.postTime);
//        hash = 97 * hash + Objects.hashCode(this.poster);
        hash = 97 * hash + Objects.hashCode(this.tags);
        hash = 97 * hash + Objects.hashCode(this.posterId);
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
        final Post other = (Post) obj;
        if (!Objects.equals(this.contet, other.contet)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.postTime, other.postTime)) {
            return false;
        }
//        if (!Objects.equals(this.poster, other.poster)) {
//            return false;
//        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.posterId, other.posterId)) {
            return false;
        }
        return true;
    }

    /**
     * To string method
     *
     * @return
     */
//    @Override
//    public String toString() {
//        return "Post{" + "id=" + id + ", contet=" + contet + ", postTime=" + postTime + ", poster=" + poster + ", tags=" + tags + '}';
//    }
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", contet=" + contet + ", postTime=" + postTime + ", tags=" + tags + '}';
    }
}
