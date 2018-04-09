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

    /**
     *
     * @param comment
     * @return
     */
    @Override
    public int addNewComment(Comment comment) {

        if (comment != null && comment.getId() == null) {

            return commentDAO.addComment(comment);
        }

        return 0;
    }

    /**
     *
     * @param comment
     * @return
     */
    @Override
    public int editComment(Comment comment) {

        if (comment != null && comment.getId() > 0) {

            return commentDAO.updateComment(comment);
        }

        return 0;
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

    /**
     *
     * @param commentId
     * @return
     */
    @Override
    public Comment getCommentById(Long commentId) {

        if (commentId > 0) {

            return commentDAO.getComment(commentId);
        }

        return null;
    }

}
