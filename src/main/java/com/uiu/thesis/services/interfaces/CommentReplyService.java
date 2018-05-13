package com.uiu.thesis.services.interfaces;

import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentReplyService {

    public int addNewCommentReply(CommentReply commentReply);

    public int editCommentReply(CommentReply commentReply);

    public CommentReply getCommentReplyById(Long id);

    public List<CommentReply> getCommentReplysByComment(Long commentId);
}
