package com.uiu.thesis.services.interfaces.forum;

import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentReplyService {

    public int addNewCommentReply(CommentReply commentReply);

    public int editCommentReply(CommentReply commentReply);

    public List<CommentReply> getCommentReplysByComment(Long commentId);
}
