package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.json.CommentsAndReplys;
import com.uiu.thesis.models.forum.json.PostComments;
import com.uiu.thesis.services.interfaces.CommentReplyService;
import com.uiu.thesis.services.interfaces.CommentService;
import com.uiu.thesis.services.interfaces.PostService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentReplyService commentReplyService;

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
            value = "/service/forum/post/user/{id}",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String getPosts(@PathVariable("id") long id) {

        List<Post> posts = postService.getPostsByUser(id);

        String postsJson;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);
        try {

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

            return "{\"edited\":\"false\"}";
        }

        return "{\"edited\":\"true\"}";
    }

    /**
     *
     * @param tags
     * @return
     */
    @RequestMapping(
            value = "/service/forum/post/search/tag",
            params = {"tags"},
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String searchPostByTag(@RequestParam("tags") String[] tags) {

        List<Post> posts = postService.getPostsByTag(tags);

        if (posts != null) {

            String postsJson;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);
            try {

                postsJson = objectMapper.writeValueAsString(posts);
            } catch (JsonProcessingException ex) {

                return "[]";
            }

            if (postsJson != null) {

                return postsJson;
            }
        }

        return "[]";
    }

    /**
     *
     * @param postId
     * @return
     */
    @RequestMapping(
            value = "/service/forum/post/comment/reply/{post_id}",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)

    public String getPostComments(@PathVariable("post_id") long postId) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);
        String postJsonString = "[]";

        //Get the post by a specific ID
        Post post = postService.getPostById(postId);

        if (post != null) {

            //Creating Post-Comments
            PostComments postComments = new PostComments();
            postComments.setId(post.getId());
            postComments.setContent(post.getContent());
            postComments.setPostTime(post.getPostTime());
            postComments.setPosterId(post.getPosterId());
            postComments.setTags(post.getTags());

            //Get comments by the post ID
            List<Comment> commentsList = commentService.getCommentsByPost(postId);

            List<CommentsAndReplys> commentAndReplysList = new ArrayList<>();

            for (Comment comment : commentsList) {

                //Create comment and comment replys list
                CommentsAndReplys commentAndReplys = new CommentsAndReplys();
                commentAndReplys.setId(comment.getId());
                commentAndReplys.setContent(comment.getContent());
                commentAndReplys.setCommentTime(comment.getCommentTime());
                commentAndReplys.setCommenterId(comment.getCommenterId());
                commentAndReplys.setIsEdited(comment.isEdited());
                commentAndReplys.setPostId(comment.getPostId());

                List<CommentReply> replys
                        = commentReplyService.getCommentReplysByComment(comment.getId());

                //add reply list to a comment
                commentAndReplys.setCommentReplys(replys);

                //add comment and its replys to a list
                commentAndReplysList.add(commentAndReplys);
            }

            //adding comments list of a post
            postComments.setComments(commentAndReplysList);

            //Creating JSON String of Post, Comments, and its replys
            try {

                postJsonString = objectMapper.writeValueAsString(postComments);
            } catch (JsonProcessingException e) {

                postJsonString = "[]";
            }
        }

        return postJsonString;
    }

}
