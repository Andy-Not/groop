package com.groop.server.service;

import com.groop.server.domain.Comment;
import com.groop.server.domain.Task;
import com.groop.server.dto.CommentDTO;
import com.groop.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class CommentImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Optional<Comment> findCommentById(Long comment_id) {
        return commentRepository.findById(comment_id);
    }
}
