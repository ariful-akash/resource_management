package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
public class TagTypeDAOImpl implements TagTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    /**
     *
     * @param tagType
     * @return
     */
    @Override
    public int addTagType(TagType tagType) {

        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(tagType);

        return Integer.valueOf(id.toString());
    }

    /**
     *
     * @param postId
     * @param tagId
     * @return
     */
    @Override
    public int mapPostTag(Long postId, Long tagId) {

        if (postId > 0 && tagId > 0) {

            Session session = sessionFactory.getCurrentSession();
            String sql = "INSERT INTO posts_tag_types(posts_id, tags_id)"
                    + "VALUES(" + postId + ", " + tagId + ")";

            try {

                Query query = session.createSQLQuery(sql);

                query.executeUpdate();

                return 1;

            } catch (HibernateException e) {

                return 0;
            }
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<TagType> getAllTagTypes() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM TagType";

        @SuppressWarnings("unchecked")
        List<TagType> tagTypes = session.createQuery(hql).list();

        return tagTypes;
    }

    @Override
    public List<TagType> getTagTypesByPost(Post post) {

        return null;
    }

    @Override
    public List<TagType> getTagTypesByPost(Long postId) {

        return null;
    }

    /**
     *
     * @param tagId
     * @return
     */
    @Override
    public TagType getTagTypeById(Long tagId) {

        if (tagId > 0) {

            Session session = sessionFactory.getCurrentSession();
            return (TagType) session.get(TagType.class, tagId);
        }

        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<TagType> getTags() {

        String sql = "SELECT * FROM tag_types";

        return getTagBySQL(sql);
    }

    /**
     *
     * @param sql
     * @return
     */
    private List<TagType> getTagBySQL(String sql) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List results = query.list();

        if (results.size() > 0) {

            List<TagType> tagTypes = new ArrayList<>();

            for (Object res : results) {

                TagType tag = new TagType();
                Map row = (Map) res;

                BigInteger idInt = (BigInteger) row.get("id");

                tag.setId(idInt.longValue());
                tag.setTag((String) row.get("tag"));

                tagTypes.add(tag);
            }

            return tagTypes;
        }

        return null;
    }
}
