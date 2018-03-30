package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(comment);

        return Integer.valueOf(id.toString());
    }

    @Override
    public int updateComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteComment(Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment getComment(Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAllComments(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAllComments(Long postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAllComments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
