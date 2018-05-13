package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.CommentReplyDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
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
public class CommentReplyDAOImpl implements CommentReplyDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param commentReply
     * @return
     */
    @Override
    public int addCommentReply(CommentReply commentReply) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(commentReply);

        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param commentReply
     * @return
     */
    @Override
    public int updateCommentReply(CommentReply commentReply) {

        if (commentReply.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(commentReply);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }
        return 0;
    }

    @Override
    public int deleteCommentReply(CommentReply commentReply) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCommentReply(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public List<CommentReply> getAllCommentReplys() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM CommentReply";

        @SuppressWarnings("unchecked")
        List<CommentReply> commentReplys = session.createQuery(hql).list();

        return commentReplys;
    }

    @Override
    public List<CommentReply> getAllCommentReplys(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param commentId
     * @return
     */
    @Override
    public List<CommentReply> getAllCommentReplys(Long commentId) {

        if (commentId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM CommentReply cr WHERE cr.commentId = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", commentId);

            @SuppressWarnings("unchecked")
            List<CommentReply> replys = query.list();

            return replys;
        }

        return null;
    }

    /**
     *
     * @param commentReplyId
     * @return
     */
    @Override
    public CommentReply getCommentReply(Long commentReplyId) {

        if (commentReplyId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (CommentReply) session.get(CommentReply.class, commentReplyId);
        }

        return null;
    }
}
