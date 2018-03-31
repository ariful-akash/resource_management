package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentReplyDAO {

    public int addCommentReply(CommentReply commentReply);

    public int updateCommentReply(CommentReply commentReply);

    public int deleteCommentReply(CommentReply commentReply);

    public int deleteCommentReply(Long id);

    public List<CommentReply> getAllCommentReplys();

    public List<CommentReply> getAllCommentReplys(Comment comment);

    public List<CommentReply> getAllCommentReplys(Long commentId);

    public CommentReply getCommentReply(Long commentReplyId);
}
