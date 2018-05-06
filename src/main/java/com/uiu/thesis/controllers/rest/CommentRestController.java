/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.services.interfaces.CommentService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashif
 */
@RestController(value = "/api/service/forum/comment")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TokenDAO tokenDAO;

    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    /**
     *
     * @param commentJson
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/comment/add",
            params = {"comment"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addCommentService(
            @RequestParam("comment") String commentJson,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);
            ObjectMapper objectMapper = new ObjectMapper();

            try {

                Comment comment = objectMapper.readValue(commentJson, Comment.class);
                if (comment != null
                        && comment.getId() == null
                        && comment.getPostId() != null
                        && comment.getContent() != null) {

                    comment.setCommenterId(userId);
                    comment.setCommentTime(new Date());
                    comment.setIsEdited(false);

                    int value = commentService.addNewComment(comment);

                    if (value != 0) {

                        return "{\"insert\":\"true\"}";
                    }
                }
            } catch (IOException e) {

                System.err.println(e.toString());
            }
        }

        return "{\"insert\":\"false\"}";
    }

    /**
     *
     * @param id
     * @param content
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/comment/edit",
            params = {"commentid", "content"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String editCommentService(
            @RequestParam("commentid") long id,
            @RequestParam("content") String content) {

        if (id > 0 && !content.isEmpty()) {

            Comment comment = commentService.getCommentById(id);

            if (comment != null && !content.equals(comment.getContent())) {

                comment.setIsEdited(true);
                comment.setContent(content);
                int value = commentService.editComment(comment);

                if (value != 0) {

                    return "{\"edit\":\"true\"}";
                }
            }
        }

        return "{\"edit\":\"false\"}";
    }
}
