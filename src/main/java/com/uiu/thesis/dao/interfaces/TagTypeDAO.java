/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface TagTypeDAO {

    public int addTagType(TagType tagType);

    public int mapPostTag(Long postId, Long tagId);

    public TagType getTagTypeById(Long tagId);

    public boolean isExist(String tag);

    public List<TagType> getTags();

    public List<TagType> getAllTagTypes();

    public List<TagType> getTagTypesByPost(Post post);

    public List<TagType> getTagTypesByPost(Long postId);
}
