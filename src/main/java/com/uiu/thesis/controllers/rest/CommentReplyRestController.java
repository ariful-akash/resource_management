package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.services.interfaces.forum.CommentReplyService;
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
@RestController(value = "/service/forum/reply")
public class CommentReplyRestController {

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     *
     * @param replyJson
     * @return
     */
    @RequestMapping(
            value = "/service/forum/reply/add",
            params = {"reply"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addNewReplyService(@RequestParam("reply") String replyJson) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            CommentReply commentReply = objectMapper.readValue(replyJson, CommentReply.class);

            if (commentReply != null
                    && commentReply.getId() == null
                    && commentReply.getReplierId() != null
                    && commentReply.getCommentId() != null
                    && commentReply.getDateTime() != null
                    && commentReply.getReply() != null
                    && commentReply.isEdited() == false) {

                int value = commentReplyService.addNewCommentReply(commentReply);

                if (value != 0) {

                    return "{\"add\":\"true\"}";
                }
            }

        } catch (IOException e) {

            System.err.println(e.toString());
        }

        return "{\"add\":\"true\"}";
    }
}
