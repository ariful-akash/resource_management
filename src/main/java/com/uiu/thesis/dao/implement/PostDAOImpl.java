package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.PostDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
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
public class PostDAOImpl implements PostDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param post
     * @return
     */
    @Override
    public int addPost(Post post) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(post);

        return Integer.valueOf(id.toString());
    }

    @Override
    public int editPost(Post oldPost, Post newPost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editPost(Long oldPostId, Post newPost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePost(Long postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param post
     * @return
     */
    @Override
    public int updatePost(Post post) {

        if (post.getId() > 0) {

            Session session = sessionFactory.getCurrentSession();
            try {

                session.update(post);
                return 1;
            } catch (Exception e) {

                return 0;
            }
        }
        return 0;
    }

    /**
     *
     * @param postId
     * @return
     */
    @Override
    public Post getPostById(Long postId) {

        if (postId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (Post) session.get(Package.class, postId);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Post> getAllPosts() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Post.class);

        @SuppressWarnings("unchecked")
        List<Post> post = criteria.list();

        return post;
    }

    @Override
    public List<Post> getPostsByPoster(HumanResource poster) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByPoster(Long posterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByDate(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByDate(Date from) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByTagType(TagType tagType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByTagTypes(List<TagType> tagTypes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
