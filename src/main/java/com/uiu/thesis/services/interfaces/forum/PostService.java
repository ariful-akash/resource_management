package com.uiu.thesis.services.interfaces.forum;

import com.uiu.thesis.models.forum.Post;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface PostService {

    public int addNewPost(Post post, Long posterId, String[] tags);

    public int editPost(Long postId, String content);

    public List<Post> getAllPosts();

    public Post getPostById(Long postId);

    public Post getPostByComment(Long commentId);

    public List<Post> getPostsByUser(Long posterId);

    public List<Post> getPostsByTag(Long tagId);
}
