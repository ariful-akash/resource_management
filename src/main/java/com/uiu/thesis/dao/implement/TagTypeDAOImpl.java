package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashif
 */
@Repository
@Transactional
public class TagTypeDAOImpl implements TagTypeDAO {

    @Override
    public boolean addTagType(TagType tagType) {

        return false;
    }

    @Override
    public List<TagType> getAllTagTypes() {

        return null;
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
