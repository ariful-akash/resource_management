/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiu.thesis.dao.interfaces;

import com.uiu.thesis.models.forum.Comment;
import com.uiu.thesis.models.forum.Post;
import java.util.List;

/**
 *
 * @author ashif
 */
public interface CommentDAO {

    public int addComment(Comment comment);

    public int updateComment(Comment comment);

    public int deleteComment(Comment comment);

    public int deleteComment(Long commentId);

    public List<Comment> getAllComments(Post post);

    public List<Comment> getAllComments(Long postId);

    public List<Comment> getAllComments();

    public Comment getComment(Long commentId);
}
