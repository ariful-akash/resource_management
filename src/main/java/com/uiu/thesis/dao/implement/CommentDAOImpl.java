package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    @Override
    public boolean addComment(Post post, Comment comment) {

        return false;
    }

    @Override
    public boolean addComment(Long postId, Comment comment) {

        return false;
    }

    @Override
    public boolean editComment(Long oldId, String newContent) {

        return false;
    }

    @Override
    public boolean deleteComment(Comment comment) {

        return false;
    }

    @Override
    public boolean deleteComment(Long commentId) {

        return false;
    }

    @Override
    public List<Comment> getAllComments(Post post) {

        return null;
    }

    @Override
    public List<Comment> getAllComments(Long postId) {

        return null;
    }

    @Override
    public Comment getComment(Long commentId) {

        return null;
    }

}
