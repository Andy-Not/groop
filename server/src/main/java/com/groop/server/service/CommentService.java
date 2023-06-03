package com.groop.server.service;

import com.groop.server.domain.Comment;
import com.groop.server.domain.Task;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface CommentService {
    void deleteComment(Comment comment);

    Optional<Comment> findCommentById(Long comment_id);
}
