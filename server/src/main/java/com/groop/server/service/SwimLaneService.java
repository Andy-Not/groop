package com.groop.server.service;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.model.Kanban;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.repository.KanbanSwimLaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author joandy alejo garcia
 */
@Service
public class SwimLaneService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private KanbanSwimLaneRepository swimLaneRepository;

    public void deleteSwimLanesInKanban(Long kanbanId){
        List<KanbanSwimLane> kanbanSwimLanes = swimLaneRepository.findAll();
        for (KanbanSwimLane swimLane : kanbanSwimLanes) {
            if (Objects.equals(swimLane.getKanban().getId(), kanbanId)){
                swimLaneRepository.delete(swimLane);
            }
        }
    }

    public SwimLaneDTO createSwimLane(Kanban kanban, String title){
        KanbanSwimLane kanbanSwimLane = new KanbanSwimLane();
        kanbanSwimLane.setKanban(kanban);
        kanbanSwimLane.setTitle(title);
        swimLaneRepository.save(kanbanSwimLane);
        return covertSwimLaneToDTO(kanbanSwimLane);
    }
    public List<SwimLaneDTO> findAllSwimLanesByKanbanId(Long kanbanId) {
        List<KanbanSwimLane> allKanbanSwimLanes = swimLaneRepository.findAll();
        List<SwimLaneDTO> allSwimLanesInKanban = new ArrayList<>();
        for (KanbanSwimLane swimLane : allKanbanSwimLanes){
            if (Objects.equals(swimLane.getKanban().getId(), kanbanId)){
                allSwimLanesInKanban.add(covertSwimLaneToDTO(swimLane));
            }
        }
        return allSwimLanesInKanban;
    }

    public SwimLaneDTO covertSwimLaneToDTO(KanbanSwimLane swimLane){
        SwimLaneDTO swimLaneDTO = new SwimLaneDTO();
        swimLaneDTO.setId(swimLane.getId());
        swimLaneDTO.setKanbanID(swimLane.getKanban().getId());
        swimLaneDTO.setTitle(swimLane.getTitle());
        swimLaneDTO.setTasks(taskService.findAllTasksBySwimLaneId(swimLane.getId()));
        return swimLaneDTO;
    }

    public KanbanSwimLane covertDTOToSwimLane(SwimLaneDTO swimLaneDTO, Kanban kanban){
        KanbanSwimLane swimLane = new KanbanSwimLane();
        swimLane.setId(swimLaneDTO.getId());
        swimLane.setTitle(swimLaneDTO.getTitle());
        swimLane.setKanban(kanban);
        return swimLane;
    }
}
