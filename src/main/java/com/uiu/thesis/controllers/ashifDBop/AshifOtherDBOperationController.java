/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.controllers.ashifDBop;

import com.github.javafaker.Faker;
import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.dao.interfaces.CommentReplyDAO;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.PostDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ashif
 */
@Controller
public class AshifOtherDBOperationController {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    @Autowired
    private CommentReplyDAO commentReplyDAO;

    /**
     * This method returns a new object of post with content and posting time
     *
     * @return
     */
    private Post getFakePost() {

        Post post = new Post();
        Faker faker = new Faker();

        post.setContet(faker.lorem().paragraph(10));
        post.setPostTime(faker.date().birthday(0, 1));

        return post;
    }

    @RequestMapping(value = "/insert/post")
    public String insertPost() {

        for (int i = 1; i <= 1000; i++) {

            Post post = getFakePost();
            postDAO.addPost(post);
        }

        return "success";
    }

    /**
     * This method returns a new object of comment with content, posting time, and whether the post is edited or not
     *
     * @param postDate
     * @return
     */
    private Comment getFakeComment(Date postDate) {

        Comment comment = new Comment();
        Faker faker = new Faker();
        Random random = new Random();

        comment.setContent(faker.lorem().paragraph(3));
        comment.setCommentTime(postDate);
        comment.setIsEdited(random.nextBoolean());

        return comment;
    }

    @RequestMapping(value = "/insert/comment")
    public String insertComment() {

        List<Post> posts = postDAO.getAllPosts();

//        for (Post post : posts) {
//
//            for (int i = 1; i <= 12; i++) {
//
//                post.getComments().add(getFakeComment(post.getPostTime()));
//            }
//
//            postDAO.updatePost(post);
//        }
        return "success";
    }

    /**
     *
     * @param commentDate
     * @return
     */
    private CommentReply getFakeCommentReply(Date commentDate) {

        CommentReply commentReply = new CommentReply();
        Faker faker = new Faker();
        Random random = new Random();

        commentReply.setReply(faker.lorem().paragraph(3));
        commentReply.setDateTime(commentDate);
        commentReply.setEdited(random.nextBoolean());

        return commentReply;
    }

    @RequestMapping(value = "/insert/commentreply")
    private String insertCommentReply() {

        List<Comment> comments = commentDAO.getAllComments();

        for (Comment comment : comments) {

            for (int i = 1; i <= 3; i++) {

                comment.getCommentReplies().add(getFakeCommentReply(comment.getCommentTime()));
            }

            commentDAO.updateComment(comment);
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/hr/post")
    public String mapHRPost() {

        List<HumanResource> hrs = humanResourceDAO.getAllHumanResources();
        List<Post> posts = postDAO.getAllPosts();

//        int j = 0;
//        for (HumanResource hr : hrs) {
//
//            for (int i = 0; i < 25; i++) {
//
//                hr.getPosts().add(posts.get(j));
//                j++;
//            }
//
//            humanResourceDAO.updateHumanResource(hr);
//        }
//
        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/hr/comment")
    public String mapHRComment() {

        List<HumanResource> hrs = humanResourceDAO.getAllHumanResources();

        int j = 1;
        int id;
        id = j;
        for (HumanResource hr : hrs) {

            for (int i = 0; i < 300; i++) {

                Comment comment = commentDAO.getComment((long) j);

                comment.setCommenterId(hr.getId());
                id = commentDAO.updateComment(comment);

                j++;

                if (id == 0) {

                    return "fail";
                }
            }
        }

        return "success";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/map/hr/commentreply")
    public String mapHRCommentReply() {

        List<HumanResource> hrs = humanResourceDAO.getAllHumanResources();

        int j = 1;
        int id;
        for (HumanResource hr : hrs) {

            for (int i = 0; i < 900; i++) {

                CommentReply commentReply = commentReplyDAO.getCommentReply((long) j);
                commentReply.setReplierId(hr.getId());
                j++;

                id = commentReplyDAO.updateCommentReply(commentReply);

                if (id == 0) {

                    return "fail";
                }
            }
        }

        return "success";
    }

}
