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

import java.util.*;

/**
 * @author joandy alejo garcia
 */
@Service
public class KanbanService {
   @Autowired
    private KanbanSwimLaneRepository kanbanSwimLaneRepository;
   @Autowired
   private SwimLaneService swimLaneService;

   @Autowired
    private KanbanRepository kanbanRepository;

    public KanbanDTO saveNewKanban(KanbanDTO kanbanDTO, User user) {
        Kanban kanban = convertKanbanDTOToKanban(kanbanDTO, user);
        List<SwimLaneDTO> swimLanes = kanbanDTO.getSwimLanes();
        kanbanRepository.save(kanban);
        return covertKanbanToDTO(kanban, swimLanes);
    }
//    public KanbanSwimLane addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO) {
//        KanbanSwimLane kanbanSwimLane = kanbanSwimLaneRepository.findById(kanban_id).get();
//        return kanbanSwimLaneRepository.save(kanbanSwimLane);
//    }

    public void addUserToKanbanById(User user, Kanban kanban){
        kanban.addUser(user);
        kanbanRepository.save(kanban);
    }
    public Optional<Kanban> findKanbanById(Long kanban_id) {
        return kanbanRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanRepository.findById(kanban_id);
    }

    public List<KanbanDTO> findAllKanban() {
        List<KanbanDTO> kanbans = new ArrayList<>();
        List<KanbanSwimLane> swimLanes = kanbanSwimLaneRepository.findAll();

        for (Kanban kanban : kanbanRepository.findAll()) {
            List<SwimLaneDTO> kanbanSwimLanes = new LinkedList<>();
            for (KanbanSwimLane swimLane : swimLanes) {
                SwimLaneDTO swimLaneDTO = swimLaneService.covertSwimLaneToDTO(swimLane);
                if (Objects.equals(swimLane.getKanban().getId(), kanban.getId())){
                    kanbanSwimLanes.add(swimLaneDTO);
                }
            }
            KanbanDTO kanbanDTO = covertKanbanToDTO(kanban, kanbanSwimLanes);
            kanbans.add(kanbanDTO);
        }
        return kanbans;
    }

    public void deleteKanban(Kanban kanban) {
        kanbanRepository.delete(kanban);
    }

    public Kanban convertKanbanDTOToKanban(KanbanDTO kanbanDTO, User user){
        Kanban kanban = new Kanban();
        kanban.setOwner(user);
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }

    public KanbanDTO covertKanbanToDTO(Kanban kanban, List<SwimLaneDTO> kanbanSwimLanes){
        KanbanDTO kanbanDTO = new KanbanDTO();
        kanbanDTO.setId(kanban.getId());
        kanbanDTO.setTitle(kanban.getTitle());
        kanbanDTO.setOwner_id(kanban.getOwner().getId());
        kanbanDTO.setSwimLanes(kanbanSwimLanes);
        return kanbanDTO;
    }

}
