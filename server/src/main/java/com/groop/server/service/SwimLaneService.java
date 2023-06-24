package com.groop.server.service;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.repository.KanbanSwimLaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class SwimLaneService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private KanbanSwimLaneRepository swimLaneRepository;

    public List<SwimLaneDTO> findSwimLaneKanbanId(Long kanban_id) {
        List<KanbanSwimLane> allKanbanSwimLanes = swimLaneRepository.findAll();
        List<SwimLaneDTO> allSwimLanesInKanban = new ArrayList<>();
        for (KanbanSwimLane swimLane : allKanbanSwimLanes){
            if (Objects.equals(swimLane.getKanban().getId(), kanban_id)){
                allSwimLanesInKanban.add(covertSwimLaneToDTO(swimLane));
            }
        }
        return allSwimLanesInKanban;
    }

    public SwimLaneDTO covertSwimLaneToDTO(KanbanSwimLane swimLane){
        SwimLaneDTO swimLaneDTO = new SwimLaneDTO();
        swimLaneDTO.setId(swimLane.getId());
        swimLaneDTO.setTitle(swimLane.getTitle());
        swimLaneDTO.setTasks(taskService.findAllTasksBySwimLaneId(swimLane.getId()));
        return swimLaneDTO;
    }
}
