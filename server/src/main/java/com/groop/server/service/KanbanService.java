package com.groop.server.service;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.model.Kanban;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.model.Task;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.model.User;
import com.groop.server.repository.KanbanRepository;
import com.groop.server.repository.KanbanSwimLaneRepository;
import com.groop.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class KanbanService {

   @Autowired
    private KanbanSwimLaneRepository kanbanSwimLaneRepository;

   @Autowired
   private TaskService taskService;

   @Autowired
    private KanbanRepository kanbanRepository;

    public KanbanDTO saveNewKanban(KanbanDTO kanbanDTO, User user) {
        kanbanRepository.save(convertKanbanDTOToKanban(kanbanDTO, user));
        return kanbanDTO;
    }
    public KanbanSwimLane addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO) {
        KanbanSwimLane kanbanSwimLane = kanbanSwimLaneRepository.findById(kanban_id).get();
        return kanbanSwimLaneRepository.save(kanbanSwimLane);
    }

    public void addUserToKanbanById(User user, Kanban kanban){
        kanban.addUser(user);
        kanbanRepository.save(kanban);
    }
    public Optional<Kanban> findKanbanById(Long kanban_id) {
        return kanbanRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanRepository.findById(kanban_id);
    }

    public Optional<KanbanSwimLane> findSwimLaneById(Long kanban_id) {
        return kanbanSwimLaneRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanSwimLaneRepository.findById(kanban_id);
    }

    public List<KanbanDTO> findAllKanban() {
        List<KanbanDTO> kanbans = new ArrayList<>();
        List<KanbanSwimLane> swimLanes = kanbanSwimLaneRepository.findAll();
        for (Kanban kanban : kanbanRepository.findAll()) {
            List<SwimLaneDTO> kanbanSwimLanes = new LinkedList<>();
            KanbanDTO kanbanDTO = new KanbanDTO();
            kanbanDTO.setTitle(kanban.getTitle());
            kanbanDTO.setOwner_id(kanban.getOwner().getId());
            for (KanbanSwimLane swimLane : swimLanes) {
                SwimLaneDTO swimLaneDTO = new SwimLaneDTO();
                swimLaneDTO.setId(swimLane.getId());
                swimLaneDTO.setTitle(swimLane.getTitle());
                swimLaneDTO.setTasks(taskService.findAllTasksBySwimLaneId(swimLane.getId()));
                if (swimLane.getKanban().getId() == kanban.getId()){
                    kanbanSwimLanes.add(swimLaneDTO);
                }
            }
            kanbanDTO.setSwimLanes(kanbanSwimLanes);
            kanbans.add(kanbanDTO);
        }
        return kanbans;
    }



    public void deleteKanban(KanbanSwimLane kanbanSwimLane) {
        kanbanSwimLaneRepository.delete(kanbanSwimLane);
    }


    public Kanban convertKanbanDTOToKanban(KanbanDTO kanbanDTO, User user){
        Kanban kanban = new Kanban();
        kanban.setOwner(user);
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
//        task.setDescription(taskDTO.getDescription());
//        task.setTitle(taskDTO.getTitle());
//        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
