package com.uiu.thesis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uiu.thesis.dao.interfaces.HumanResourceDAO;
import com.uiu.thesis.dao.interfaces.TokenDAO;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.services.interfaces.CommentReplyService;
import java.io.IOException;
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
@RestController(value = "/api/service/forum/reply")
public class CommentReplyRestController {

    @Autowired
    private CommentReplyService commentReplyService;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private HumanResourceDAO humanResourceDAO;

    /**
     *
     * @param replyJson
     * @param session
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/reply/add",
            params = {"reply"},
            produces = {"application/json;charset:UTF-8"},
            method = RequestMethod.POST)
    public String addNewReplyService(
            @RequestParam("reply") String replyJson,
            HttpSession session) {

        String token = (String) session.getAttribute("token");
        if (token != null && tokenDAO.isTokenExist(token)) {

            long userId = tokenDAO.getUserId(token);

            ObjectMapper objectMapper = new ObjectMapper();
            try {

                CommentReply commentReply = objectMapper.readValue(replyJson, CommentReply.class);

                /*
                 * The replier id must be implemented later
                 */
                if (commentReply != null
                        && commentReply.getId() == null
                        && commentReply.getCommentId() != null
                        && commentReply.getReply() != null) {

                    commentReply.setReplier(humanResourceDAO.getHumanResource(userId));
                    commentReply.setDateTime(new Date());
                    commentReply.setEdited(false);

                    int value = commentReplyService.addNewCommentReply(commentReply);

                    if (value != 0) {

                        return "{\"insert\":\"true\"}";
                    }
                }

            } catch (IOException e) {

                System.err.println(e.toString());
            }
        }

        return "{\"insert\":\"true\"}";
    }

    /**
     *
     * @param id
     * @param reply
     * @return
     */
    @RequestMapping(
            value = "/api/service/forum/reply/edit",
            params = {"replyid", "reply"},
            produces = {"application/json;charset:UtF-8"},
            method = RequestMethod.POST)
    public String editCommentReplyService(
            @RequestParam("replyid") long id,
            @RequestParam("reply") String reply) {

        if (id > 0 && !reply.isEmpty()) {

            CommentReply commentReply = commentReplyService.getCommentReplyById(id);

            if (commentReply != null && !reply.equals(commentReply.getReply())) {

                commentReply.setReply(reply);
                commentReply.setEdited(true);

                int value = commentReplyService.editCommentReply(commentReply);

                if (value != 0) {

                    return "{\"edit\":\"true\"}";
                }
            }
        }

        return "{\"edit\":\"false\"}";
    }
}
