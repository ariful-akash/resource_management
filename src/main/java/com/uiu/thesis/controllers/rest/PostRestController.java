package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.services.interfaces.forum.PostService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/forum/post")
public class PostRestController {

    @Autowired
    private PostService postService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(
            value = "/forum/post/add",
            params = {"post", "poster", "tags"},
            method = RequestMethod.POST)
    public String testPostService(
            @RequestParam("post") String postJson,
            @RequestParam("poster") long posterId,
            @RequestParam("tags") String tagJson) {

        try {

            Post post = objectMapper.readValue(postJson, Post.class);
            String[] tags = objectMapper.readValue(
                    tagJson,
                    TypeFactory.defaultInstance().constructArrayType(String.class));

            postService.addNewPost(post, posterId, tags);
        } catch (IOException e) {

            return "{\"insert\":\"false\"}";
        }

        return "{\"insert\":\"true\"}";
    }
}
