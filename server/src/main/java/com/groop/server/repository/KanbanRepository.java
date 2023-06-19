package com.groop.server.repository;

import com.groop.server.model.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author joandy alejo garcia
 */
public interface KanbanRepository extends JpaRepository<Kanban, Long> {
}
