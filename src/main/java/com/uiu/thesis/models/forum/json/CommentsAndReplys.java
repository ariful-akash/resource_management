package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentsAndReplys extends Comment {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private HumanResource commenter;

    private List<CommentReply> commentReplys;

    /**
     * Constructor
     */
    public CommentsAndReplys() {
    }

    /**
     * Getter And Setter
     *
     * @return
     */
    public List<CommentReply> getCommentReplys() {
        return commentReplys;
    }

    public void setCommentReplys(List<CommentReply> commentReplys) {
        this.commentReplys = commentReplys;
    }

    public HumanResource getCommenter() {
        return commenter;
    }

    public void setCommenter(HumanResource commenter) {
        this.commenter = commenter;
    }

}
