package com.uiu.thesis.models.forum.json;

import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.user.HumanResource;

/**
 *
 * @author ashif
 */
public class PostJson extends Post {

    private HumanResource poster;

    public PostJson(HumanResource poster) {
        this.poster = poster;
    }

    public HumanResource getPoster() {
        return poster;
    }

    public void setPoster(HumanResource poster) {
        this.poster = poster;
    }

}
