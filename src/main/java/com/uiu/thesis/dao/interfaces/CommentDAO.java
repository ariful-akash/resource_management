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

    public boolean addComment(Post post, Comment comment);

    public boolean addComment(Long postId, Comment comment);

    public boolean editComment(Long oldId, String newContent);

    public boolean deleteComment(Comment comment);

    public boolean deleteComment(Long commentId);

    public List<Comment> getAllComments(Post post);

    public List<Comment> getAllComments(Long postId);

    public Comment getComment(Long commentId);
}
