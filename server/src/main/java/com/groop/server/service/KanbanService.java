package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;

/**
 * @author joandy alejo garcia
 */
public interface KanbanService {
    Kanban saveNewKanban(KanbanDTO kanbanDTO);

    Kanban addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO);
}
