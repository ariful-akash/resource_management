package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
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

    /**
     *
     * @param comment
     * @return
     */
    @Override
    public int updateComment(Comment comment) {

        if (comment != null && comment.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(comment);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }

        return 0;
    }

    @Override
    public int deleteComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteComment(Long commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param commentId
     * @return
     */
    @Override
    public Comment getComment(Long commentId) {

        if (commentId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Comment) session.get(Comment.class, commentId);
        }

        return null;
    }

    @Override
    public List<Comment> getAllComments(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param postId
     * @return
     */
    @Override
    public List<Comment> getAllComments(Long postId) {

        if (postId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Comment cm WHERE cm.postId = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", postId);

            @SuppressWarnings("unchecked")
            List<Comment> comments = query.list();

            return comments;
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Comment> getAllComments() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Comment";

        @SuppressWarnings("unchecked")
        List<Comment> comments = session.createQuery(hql).list();

        return comments;
    }

}
