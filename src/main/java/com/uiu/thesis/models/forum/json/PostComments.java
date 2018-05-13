package com.uiu.thesis.models.forum.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.user.HumanResource;
import java.util.List;

/**
 *
 * @author ashif
 */
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY)
public class PostComments extends Post {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private HumanResource poster;

    private List<CommentsAndReplys> comments;

    /**
     * Constructor
     *
     */
    public PostComments() {

    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public HumanResource getPoster() {
        return poster;
    }

    public void setPoster(HumanResource poster) {
        this.poster = poster;
    }

    public List<CommentsAndReplys> getComments() {
        return comments;
    }

    public void setComments(List<CommentsAndReplys> comments) {
        this.comments = comments;
    }

}
