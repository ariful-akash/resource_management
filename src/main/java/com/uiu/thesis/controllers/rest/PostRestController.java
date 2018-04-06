package com.uiu.thesis.controllers.rest;

import com.github.javafaker.Faker;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.services.interfaces.forum.PostService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController
public class PostRestController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/rest/test/post")
    public String testPostService() {

        Post post = new Post();
        String[] hh = {"food", "canteen"};

        post.setContet(new Faker().lorem().paragraph());
        post.setPostTime(new Date());

        postService.addNewPost(post, (long) 104, hh);

        return "done";
    }
}
