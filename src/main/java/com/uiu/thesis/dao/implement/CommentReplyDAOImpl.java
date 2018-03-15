package com.uiu.thesis.dao.implement;

import com.uiu.thesis.dao.interfaces.CommentReplyDAO;
import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.CommentReply;
import java.util.List;

/**
 *
 * @author ashif
 */
public class CommentReplyDAOImpl implements CommentReplyDAO {

    @Override
    public boolean addCommentReply(CommentReply commentReply, Comment comment) {

        return false;
    }

    @Override
    public boolean addCommentReply(CommentReply commentReply, Long commentId) {

        return false;
    }

    @Override
    public boolean editCommentReply(CommentReply oldCommentReply, String newContent) {

        return false;
    }

    @Override
    public boolean editCommentReply(Long oldReplyId, String newContent) {

        return false;
    }

    @Override
    public boolean deleteCommentReply(CommentReply commentReply) {

        return false;
    }

    @Override
    public boolean deleteCommentReply(Long id) {

        return false;
    }

    @Override
    public List<CommentReply> getAllCommentReplys(Comment comment) {

        return null;
    }

    @Override
    public List<CommentReply> getAllCommentReplys(Long commentId) {

        return null;
    }

    @Override
    public CommentReply getCommentReply(Long commentReplyId) {

        return null;
    }

}
