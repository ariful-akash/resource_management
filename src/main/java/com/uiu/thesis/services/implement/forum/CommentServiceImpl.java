package com.uiu.thesis.services.implement.forum;

import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.services.interfaces.forum.CommentService;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public int addNewComment(Comment comment, Long postId, Long commenterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param postId
     * @return
     */
    @Override
    public List<Comment> getCommentsByPost(Long postId) {

        if (postId > 0) {

            return commentDAO.getAllComments(postId);
        }

        return null;
    }

    @Override
    public Comment getCommentById(Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
