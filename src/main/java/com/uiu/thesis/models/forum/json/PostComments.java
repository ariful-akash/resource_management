package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.forum.Post;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class PostComments extends Post {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private List<CommentsAndReplys> comments;

    /**
     * Constructor
     *
     * @param post
     */
    public PostComments() {

    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public List<CommentsAndReplys> getComments() {
        return comments;
    }

    public void setComments(List<CommentsAndReplys> comments) {
        this.comments = comments;
    }

}
