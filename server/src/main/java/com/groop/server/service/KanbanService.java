package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface KanbanService {
    Kanban saveNewKanban(KanbanDTO kanbanDTO);

    Kanban addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO);

    Optional<Kanban> findKanbanById(Long kanban_id);

    List<Kanban> findAllKanban();

    void deleteKanban(Kanban kanban);

}
