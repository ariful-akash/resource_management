package com.uiu.thesis.services.implement.forum;

import com.uiu.thesis.models.forum.CommentReply;
import com.uiu.thesis.services.interfaces.forum.CommentReplyService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashif
 */
@Service
@Transactional
public class CommentReplyServiceImpl implements CommentReplyService {

    @Override
    public int addNewCommentReply(CommentReply commentReply, Long replierId, Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editCommentReply(CommentReply commentReply) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommentReply> getCommentReplysByComment(Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
