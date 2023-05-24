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
    public Kanban saveNewKanban(KanbanDTO kanbanDTO) {
        return kanbanRepository.save(convertKanbanDTOToKanban(kanbanDTO));
    }

    public Kanban convertKanbanDTOToKanban(KanbanDTO kanbanDTO){
        Kanban kanban = new Kanban();
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }
}
