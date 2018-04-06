package com.uiu.thesis.services.implement.forum;

import com.uiu.thesis.dao.interfaces.CommentDAO;
import com.uiu.thesis.dao.interfaces.PostDAO;
import com.uiu.thesis.dao.interfaces.TagTypeDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import com.uiu.thesis.models.forum.TagType;
import com.uiu.thesis.services.interfaces.forum.PostService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashif
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TagTypeDAO tagTypeDAO;

    /**
     *
     * @param post
     * @param posterId
     * @return
     */
    @Override
    public int addNewPost(Post post, Long posterId) {

        if (post != null && post.getId() == 0 && post.getContet().length() > 0 && posterId > 0) {

            post.setPosterId(posterId);
            postDAO.addPost(post);

            return 1;
        }

        return 0;
    }

    /**
     *
     * @param post
     * @return
     */
    @Override
    public int editPost(Post post) {

        if (post != null && post.getId() > 0) {

            postDAO.updatePost(post);
        }

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Post> getAllPosts() {

        return postDAO.getAllPosts();
    }

    /**
     *
     * @param postId
     * @return
     */
    @Override
    public Post getPostById(Long postId) {

        return postDAO.getPostById(postId);
    }

    /**
     *
     * @param commentId
     * @return
     */
    @Override
    public Post getPostByComment(Long commentId) {

        Comment comment = commentDAO.getComment(commentId);

        if (comment != null) {

            return postDAO.getPostById(comment.getPostId());
        }

        return null;
    }

    /**
     *
     * @param posterId
     * @return
     */
    @Override
    public List<Post> getPostsByUser(Long posterId) {

        if (posterId > 0) {

            return postDAO.getPostsByPoster(posterId);
        }

        return null;
    }

    /**
     *
     * @param tagId
     * @return
     */
    @Override
    public List<Post> getPostsByTag(Long tagId) {

        if (tagId > 0) {

            TagType tag = tagTypeDAO.getTagTypeById(tagId);

            if (tag != null) {

                return tag.getPosts();
            }
        }

        return null;
    }

}
