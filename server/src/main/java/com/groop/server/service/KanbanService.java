package com.groop.server.service;

import com.groop.server.model.KanbanSwimLane;
import com.groop.server.model.Task;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.repository.KanbanSwimLaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class KanbanService {

   @Autowired
    private KanbanSwimLaneRepository kanbanSwimLaneRepository;

    public KanbanSwimLane saveNewKanban(KanbanDTO kanbanDTO) {
        return kanbanSwimLaneRepository.save(convertKanbanDTOToKanban(kanbanDTO));
    }

    public KanbanSwimLane addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO) {
        KanbanSwimLane kanbanSwimLane = kanbanSwimLaneRepository.findById(kanban_id).get();
        return kanbanSwimLaneRepository.save(kanbanSwimLane);
    }

    public Optional<KanbanSwimLane> findKanbanById(Long kanban_id) {
        return kanbanSwimLaneRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanSwimLaneRepository.findById(kanban_id);
    }

    public List<KanbanSwimLane> findAllKanban() {
        List<KanbanSwimLane> kanbanSwimLanes = new ArrayList<>();
        kanbanSwimLaneRepository.findAll().forEach(kanbanSwimLanes::add);
        return kanbanSwimLanes;
    }

    public void deleteKanban(KanbanSwimLane kanbanSwimLane) {
        kanbanSwimLaneRepository.delete(kanbanSwimLane);
    }


    public KanbanSwimLane convertKanbanDTOToKanban(KanbanDTO kanbanDTO){
        KanbanSwimLane kanbanSwimLane = new KanbanSwimLane();
        kanbanSwimLane.setTitle(kanbanDTO.getTitle());
        return kanbanSwimLane;
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
//        task.setDescription(taskDTO.getDescription());
//        task.setTitle(taskDTO.getTitle());
//        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
