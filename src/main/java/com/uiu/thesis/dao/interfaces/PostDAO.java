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

    public int addPost(Post post);

    public int editPost(Post oldPost, Post newPost);

    public int editPost(Long oldPostId, Post newPost);

    public int deletePost(Post post);

    public int deletePost(Long postId);

    public int updatePost(Post post);

    public Post getPostById(Long postId);

    public List<Post> getAllPosts();

    public List<Post> getAllPosts(int limit);

    public List<Post> getPostsByPoster(HumanResource poster);

    public List<Post> getPostsByPoster(Long posterId);

    public List<Post> getPostsByDate(Date from, Date to);

    public List<Post> getPostsByDate(Date from);

    public List<Post> getPostsByTagType(TagType tagType);

    public List<Long> getPostsIdByTagType(String tag);

    public List<Long> getPostsIdByTagTypes(List<String> tags);
}
