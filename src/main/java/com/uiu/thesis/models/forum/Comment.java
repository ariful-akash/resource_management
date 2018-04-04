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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ashif
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment", nullable = false, length = 2000)
    private String content;

    @Column(name = "comment_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date commentTime;

    @Column(name = "edited", nullable = false)
    private boolean edited;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentReply> commentReplies;

    @Column(name = "commenter_id")
    private Long commenterId;

    @Column(name = "post_id")
    private Long postId;

    /**
     * Constructor
     */
    public Comment() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setIsEdited(boolean isEdited) {
        this.edited = isEdited;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

//    public HumanResource getCommenter() {
//        return commenter;
//    }
//
//    public void setCommenter(HumanResource commenter) {
//        this.commenter = commenter;
//    }
    public List<CommentReply> getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(List<CommentReply> commentReplies) {
        this.commentReplies = commentReplies;
    }

    public Long getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    /**
     * Equals and hash method
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.content);
        hash = 59 * hash + Objects.hashCode(this.commentTime);
//        hash = 59 * hash + Objects.hashCode(this.commenter);
        hash = 59 * hash + Objects.hashCode(this.commentReplies);
        hash = 59 * hash + Objects.hashCode(this.edited);
        hash = 59 * hash + Objects.hashCode(this.postId);
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.commentTime, other.commentTime)) {
            return false;
        }
//        if (!Objects.equals(this.commenter, other.commenter)) {
//            return false;
//        }
        if (!Objects.equals(this.commentReplies, other.commentReplies)) {
            return false;
        }
        if (!Objects.equals(this.edited, other.edited)) {
            return false;
        }
        if (!Objects.equals(this.postId, other.postId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + ", commentTime=" + commentTime + ", edited=" + edited + ", commentReplies=" + commentReplies + '}';
    }

}
