package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.PostDAO;
import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

    @Autowired
    private TagTypeDAO tagTypeDAO;

    /**
     *
     * @param sql
     * @return
     */
    private List<Post> getPostsBySQL(String sql) {

        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);

        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List results = query.list();

        List<Post> posts = new ArrayList<>();

        for (Object result : results) {

            Post post = new Post();

            Map row = (Map) result;

            BigInteger idInt = (BigInteger) row.get("id");

            post.setId(idInt.longValue());
            post.setContent((String) row.get("content"));
            post.setPostTime((Date) row.get("post_time"));
            post.setPosterId((long) row.get("poster_id"));

            posts.add(post);
        }

        return posts;
    }

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

        if (post != null && post.getId() > 0) {

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
            return (Post) session.get(Post.class, postId);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Post> getAllPosts() {

        List<Post> posts = getAllPosts(100);

        return posts;
    }

    /**
     *
     * @param limit
     * @return
     */
    @Override
    public List<Post> getAllPosts(int limit) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Post post ORDER BY post.postTime DESC";

        Query query = session.createQuery(hql);
        query.setMaxResults(limit);

        @SuppressWarnings("unchecked")
        List<Post> posts = query.list();

        return posts;
    }

    @Override
    public List<Post> getPostsByPoster(HumanResource poster) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param posterId
     * @return
     */
    @Override
    public List<Post> getPostsByPoster(Long posterId) {

        if (posterId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Post post "
                    + "WHERE post.posterId = :posterId "
                    + "ORDER BY post.postTime DESC";
            Query query = session.createQuery(hql);
            query.setParameter("posterId", posterId);

            @SuppressWarnings("unchecked")
            List<Post> posts = query.list();

            return posts;
        }

        return null;
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

    /**
     *
     * @param sql
     * @return
     */
    private List<Long> getPostsIdByTagSQL(String sql) {

        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);

        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List results = query.list();

        List<Long> postsId = new ArrayList<>();

        for (Object result : results) {

            Map row = (Map) result;

            BigInteger idInt = (BigInteger) row.get("posts_id");

            postsId.add(idInt.longValue());
        }

        return postsId;
    }

    /**
     *
     * @param tag
     * @return
     */
    @Override
    public List<Long> getPostsIdByTagType(String tag) {

        if (tag != null) {

            Long id = tagTypeDAO.isExist(tag);

            if (id != 0) {

                String sql = "select posts_id from posts_tag_types"
                        + " where tags_id = " + id;

                return getPostsIdByTagSQL(sql);
            }
        }

        return null;
    }

    /**
     *
     * @param tags
     * @return
     */
    @Override
    public List<Long> getPostsIdByTagTypes(List<String> tags) {

        List<Long> resultIds = getPostsIdByTagType(tags.get(0));

        for (int i = 1; i < tags.size(); i++) {

            List<Long> ids = getPostsIdByTagType(tags.get(i));

            resultIds.retainAll(ids);
        }

        return resultIds;
    }

}
