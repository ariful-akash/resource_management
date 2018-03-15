package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.PostDAO;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public class PostDAOImpl implements PostDAO {

    @Override
    public boolean addPost(Post post) {

        return true;
    }

    @Override
    public boolean editPost(Post oldPost, Post newPost) {

        return true;
    }

    @Override
    public boolean editPost(Long oldPostId, Post newPost) {

        return true;
    }

    @Override
    public boolean deletePost(Post post) {

        return true;
    }

    @Override
    public boolean deletePost(Long postId) {

        return true;
    }

    @Override
    public Post getPostById(Long postId) {

        return null;
    }

    @Override
    public List<Post> getAllPosts() {

        return null;
    }

    @Override
    public List<Post> getPostsByPoster(HumanResource poster) {

        return null;
    }

    @Override
    public List<Post> getPostsByPoster(Long posterId) {

        return null;
    }

    @Override
    public List<Post> getPostsByDate(Date from, Date to) {

        return null;
    }

    @Override
    public List<Post> getPostsByDate(Date from) {

        return null;
    }

    @Override
    public List<Post> getPostsByTagType(TagType tagType) {

        return null;
    }

    @Override
    public List<Post> getPostsByTagTypes(List<TagType> tagTypes) {

        return null;
    }

}
