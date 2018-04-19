package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.json.CommentsAndReplys;
import com.uiu.thesis.models.forum.json.PostComments;
import com.uiu.thesis.models.forum.json.PostJson;
import com.uiu.thesis.services.interfaces.CommentReplyService;
import com.uiu.thesis.services.interfaces.CommentService;
import com.uiu.thesis.services.interfaces.PostService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RestController(value = "/api/service/forum/post")
public class PostRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentReplyService commentReplyService;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     * Get first 100 recent post
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post",
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.GET)
    public String getRecentPost(HttpSession session) {

        String token = (String) session.getAttribute("token");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        if (token != null && tokenDAO.isTokenExist(token)) {

            List<Post> justPosts = postService.getAllPosts();

            List<PostJson> posts = new ArrayList<>();

            for (Post justPost : justPosts) {

                PostJson postJson = new PostJson();

                postJson.setId(justPost.getId());
                postJson.setContent(justPost.getContent());
                postJson.setPostTime(justPost.getPostTime());
                postJson.setPosterId(justPost.getPosterId());
                postJson.setTags(justPost.getTags());
                postJson.setPoster(humanResourceDAO.getHumanResource(justPost.getPosterId()));

                posts.add(postJson);
            }

            try {

                return objectMapper.writeValueAsString(posts);
            } catch (JsonProcessingException ex) {

                System.err.println(ex.toString());
            }
        }

        return "[]";
    }

    /**
     * Add a new post
     *
     * @param postJson
     * @param tagJson
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/add",
            params = {"post", "tags"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})

    public String addNewPost(
            @RequestParam("post") String postJson,
            @RequestParam("tags") String tagJson,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long posterId = tokenDAO.getUserId(token);

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
        }

        return "{\"insert\":\"true\"}";
    }

    /**
     * returns the posts of a certain user
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/user/{id}",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String getUserPosts(
            @PathVariable("id") long id,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            List<Post> justPosts = postService.getPostsByUser(id);

            //adding user with post
            List<PostJson> postJsons = new ArrayList<>();
            for (Post post : justPosts) {

                PostJson postJson = new PostJson();

                postJson.setId(post.getId());
                postJson.setContent(post.getContent());
                postJson.setPostTime(post.getPostTime());
                postJson.setPosterId(post.getPosterId());
                postJson.setTags(post.getTags());
                postJson.setPoster(humanResourceDAO.getHumanResource(post.getPosterId()));

                postJsons.add(postJson);
            }

            String json;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);
            try {

                json = objectMapper.writeValueAsString(postJsons);
            } catch (JsonProcessingException ex) {

                return "[]";
            }

            if (json != null) {

                return json;
            }
        }

        return "[]";
    }

    /**
     * returns the posts of a logged in user
     *
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/own",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String getOwnerPosts(HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            long id = tokenDAO.getUserId(token);

            List<Post> justPosts = postService.getPostsByUser(id);

            //adding user with post
            List<PostJson> postJsons = new ArrayList<>();
            for (Post post : justPosts) {

                PostJson postJson = new PostJson();

                postJson.setId(post.getId());
                postJson.setContent(post.getContent());
                postJson.setPostTime(post.getPostTime());
                postJson.setPosterId(post.getPosterId());
                postJson.setTags(post.getTags());
                postJson.setPoster(humanResourceDAO.getHumanResource(post.getPosterId()));

                postJsons.add(postJson);
            }

            String json;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);
            try {

                json = objectMapper.writeValueAsString(postJsons);
            } catch (JsonProcessingException ex) {

                return "[]";
            }

            if (json != null) {

                return json;
            }
        }

        return "[]";
    }

    /**
     * update an edited post
     *
     * @param newContent
     * @param postId
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/edit",
            params = {"content", "postid"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})

    public String editPost(
            @RequestParam("content") String newContent,
            @RequestParam("postid") long postId,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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
        }

        return "{\"edited\":\"true\"}";
    }

    /**
     * Search posts by tags
     *
     * @param tags
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/search/tag",
            params = {"tags"},
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})

    public String searchPostByTag(
            @RequestParam("tags") String[] tags,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

            List<Post> justPosts = postService.getPostsByTag(tags);
            List<PostJson> posts = new ArrayList<>();

            for (Post justPost : justPosts) {

                PostJson postJson = new PostJson();

                postJson.setId(justPost.getId());
                postJson.setContent(justPost.getContent());
                postJson.setPostTime(justPost.getPostTime());
                postJson.setPosterId(justPost.getPosterId());
                postJson.setTags(justPost.getTags());
                postJson.setPoster(humanResourceDAO.getHumanResource(justPost.getPosterId()));

                posts.add(postJson);
            }

            if (posts.size() > 0) {

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
        }

        return "[]";
    }

    /**
     * returns comment & comment replies with a post
     *
     * @param postId
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/post/comment/reply/{post_id}",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)

    public String getPostComments(
            @PathVariable("post_id") long postId,
            HttpSession session) {

        String token = (String) session.getAttribute("token");

        if (token != null && tokenDAO.isTokenExist(token)) {

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
                postComments.setPoster(humanResourceDAO.getHumanResource(post.getPosterId()));

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
                    commentAndReplys.setCommenter(humanResourceDAO.getHumanResource(comment.getCommenterId()));

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

        return "[]";
    }

}
