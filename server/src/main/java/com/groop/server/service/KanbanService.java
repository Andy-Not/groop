package com.groop.server.service;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.model.Kanban;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.model.User;
import com.groop.server.repository.KanbanRepository;
import com.groop.server.repository.KanbanSwimLaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author joandy alejo garcia
 */
@Service
public class KanbanService {
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

    public void addUserToKanbanById(User user, Kanban kanban){
        kanban.addUser(user);
        kanbanRepository.save(kanban);
    }
    public Optional<Kanban> findKanbanById(Long kanban_id) {
        return kanbanRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanRepository.findById(kanban_id);
    }

    public List<KanbanDTO> findAllUserKanban(User user) {
        List<KanbanDTO> kanbans = new ArrayList<>();
        for (Kanban kanban : kanbanRepository.findAll()) {
            if (Objects.equals(kanban.getOwner().getId(), user.getId()) || kanban.getUsers().contains(user)){
                List<SwimLaneDTO> swimLaneDTOS = swimLaneService.findAllSwimLanesByKanbanId(kanban.getId());
                KanbanDTO kanbanDTO = covertKanbanToDTO(kanban, swimLaneDTOS);
                kanbans.add(kanbanDTO);
            }
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
