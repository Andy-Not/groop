package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class KanbanServiceImpl implements KanbanService{

   @Autowired
    private KanbanRepository kanbanRepository;


    @Override
    public Kanban addNewKanban(KanbanDTO kanbanDTO) {
        return null;
    }
}
