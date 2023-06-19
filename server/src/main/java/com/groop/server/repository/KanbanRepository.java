package com.groop.server.repository;

import com.groop.server.model.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface KanbanRepository extends JpaRepository<Kanban, Long> {

    Optional<Kanban> findAllByTitle(String title);

}
