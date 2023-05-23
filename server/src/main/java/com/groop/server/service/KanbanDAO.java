package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.dto.KanbanDTO;

/**
 * @author joandy alejo garcia
 */
public interface KanbanDAO {
    Kanban newKanban(KanbanDTO kanbanDTO);
}
