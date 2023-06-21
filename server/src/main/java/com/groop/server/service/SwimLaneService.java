package com.groop.server.service;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.repository.KanbanSwimLaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class SwimLaneService {
    @Autowired
    private TaskService taskService;

    public SwimLaneDTO covertSwimLaneToDTO(KanbanSwimLane swimLane){
        SwimLaneDTO swimLaneDTO = new SwimLaneDTO();
        swimLaneDTO.setId(swimLane.getId());
        swimLaneDTO.setTitle(swimLane.getTitle());
        swimLaneDTO.setTasks(taskService.findAllTasksBySwimLaneId(swimLane.getId()));
        return swimLaneDTO;
    }
}
