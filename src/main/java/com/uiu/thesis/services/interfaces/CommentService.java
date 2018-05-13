package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.forum.Comment;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentService {

    public int addNewComment(Comment comment);

    public int editComment(Comment comment);

    public List<Comment> getCommentsByPost(Long postId);

    public Comment getCommentById(Long commentId);
}
