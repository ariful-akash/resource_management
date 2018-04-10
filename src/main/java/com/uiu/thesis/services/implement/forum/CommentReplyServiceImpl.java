package com.uiu.thesis.services.implement.forum;

import com.uiu.thesis.dao.interfaces.CommentReplyDAO;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.services.interfaces.forum.CommentReplyService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashif
 */
@Service
@Transactional
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    private CommentReplyDAO commentReplyDAO;

    /**
     *
     * @param commentReply
     * @return
     */
    @Override
    public int addNewCommentReply(CommentReply commentReply) {

        if (commentReply != null && commentReply.getId() == null) {

            return commentReplyDAO.addCommentReply(commentReply);
        }

        return 0;
    }

    @Override
    public int editCommentReply(CommentReply commentReply) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param commentId
     * @return
     */
    @Override
    public List<CommentReply> getCommentReplysByComment(Long commentId) {

        if (commentId > 0) {

            return commentReplyDAO.getAllCommentReplys(commentId);
        }

        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public CommentReply getCommentReplyById(Long id) {

        if (id > 0) {

            return commentReplyDAO.getCommentReply(id);
        }

        return null;
    }

}
