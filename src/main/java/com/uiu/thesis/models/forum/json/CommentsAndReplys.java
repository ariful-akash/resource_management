package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentsAndReplys extends Comment {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

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

}
