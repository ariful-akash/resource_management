package com.uiu.thesis.services.implement;

import com.uiu.thesis.dao.interfaces.CommentReplyDAO;
import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.services.interfaces.CommentReplyService;
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

    /**
     *
     * @param commentReply
     * @return
     */
    @Override
    public int editCommentReply(CommentReply commentReply) {

        if (commentReply != null && commentReply.getId() > 0) {

            return commentReplyDAO.updateCommentReply(commentReply);
        }

        return 0;
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
