package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.services.interfaces.forum.PostService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/service/forum/post")
public class PostRestController {

    @Autowired
    private PostService postService;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @param postJson
     * @param posterId
     * @param tagJson
     * @return
     */
    @RequestMapping(
            value = "/service/forum/post/add",
            params = {"post", "poster", "tags"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})

    public String addNewPost(
            @RequestParam("post") String postJson,
            @RequestParam("poster") long posterId,
            @RequestParam("tags") String tagJson) {

        ObjectMapper objectMapper = new ObjectMapper();

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

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/service/forum/post/get",
            params = {"user"},
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String getPosts(
            @RequestParam("user") long id) {

        List<Post> posts = postService.getPostsByUser(id);

        String postsJson;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            objectMapper.setDateFormat(df);
            postsJson = objectMapper.writeValueAsString(posts);
        } catch (JsonProcessingException ex) {

            return "[]";
        }

        if (postsJson != null) {

            return postsJson;
        }

        return "[]";
    }

    /**
     *
     * @param newContent
     * @param postId
     * @return
     */
    @RequestMapping(
            value = "/service/forum/post/edit",
            params = {"content", "postid"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})

    public String editPost(
            @RequestParam("content") String newContent,
            @RequestParam("postid") long postId) {

        int returnVal;

        try {

            returnVal = postService.editPost(postId, newContent);

        } catch (Exception e) {

            returnVal = 0;
            System.err.println(e.toString());
        }

        if (returnVal == 0) {

            return "{\"edit\":\"false\"}";
        }

        return "{\"edit\":\"true\"}";
    }
}
