package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentReplyDAO {

    public boolean addCommentReply(CommentReply commentReply, Comment comment);

    public boolean addCommentReply(CommentReply commentReply, Long commentId);

    public boolean editCommentReply(CommentReply oldCommentReply, String newContent);

    public boolean editCommentReply(Long oldReplyId, String newContent);

    public boolean deleteCommentReply(CommentReply commentReply);

    public boolean deleteCommentReply(Long id);

    public List<CommentReply> getAllCommentReplys(Comment comment);

    public List<CommentReply> getAllCommentReplys(Long commentId);

    public CommentReply getCommentReply(Long commentReplyId);
}
