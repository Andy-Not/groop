package com.groop.server.repository;

import com.groop.server.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author joandy alejo garcia
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
