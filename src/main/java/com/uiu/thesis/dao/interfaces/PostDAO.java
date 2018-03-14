package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.models.user.HumanResource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface PostDAO {

    public boolean addPost(Post post);

    public boolean editPost(Post oldPost, Post newPost);

    public boolean editPost(Long oldPostId, Post newPost);

    public boolean deletePost(Post post);

    public boolean deletePost(Long postId);

    public Post getPostById(Long postId);

    public List<Post> getAllPosts();

    public List<Post> getPostsByPoster(HumanResource poster);

    public List<Post> getPostsByPoster(Long posterId);

    public List<Post> getPostsByDate(Date from, Date to);

    public List<Post> getPostsByDate(Date from);

    public List<Post> getPostsByTagType(TagType tagType);

    public List<Post> getPostsByTagTypes(List<TagType> tagTypes);
}
