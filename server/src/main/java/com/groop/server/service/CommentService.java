package com.groop.server.service;

import com.groop.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public void deleteComment(com.groop.server.model.Comment comment) {
        commentRepository.delete(comment);
    }

    public Optional<com.groop.server.model.Comment> findCommentById(Long comment_id) {
        return commentRepository.findById(comment_id);
    }
}
