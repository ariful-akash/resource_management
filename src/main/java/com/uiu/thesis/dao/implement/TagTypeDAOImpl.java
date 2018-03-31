package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
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
public class TagTypeDAOImpl implements TagTypeDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public int addTagType(TagType tagType) {

        return 0;
    }

    @Override
    public List<TagType> getAllTagTypes() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(TagType.class);

        @SuppressWarnings("unchecked")
        List<TagType> tagTypes = criteria.list();

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

}
