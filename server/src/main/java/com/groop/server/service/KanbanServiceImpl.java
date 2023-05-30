package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.domain.Task;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.repository.KanbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Kanban addNewTaskToKanban(Long kanban_id, TaskDTO taskDTO) {
        Kanban kanban = kanbanRepository.findById(kanban_id).get();
        kanban.addTask(convertTaskDTOtoTask(taskDTO));
        return kanbanRepository.save(kanban);
    }

    @Override
    public Optional<Kanban> findKanbanById(Long kanban_id) {
        return kanbanRepository.findById(kanban_id).isEmpty() ? Optional.empty() :  kanbanRepository.findById(kanban_id);
    }

    @Override
    public void deleteKanban(Kanban kanban) {
        kanbanRepository.delete(kanban);
    }


    public Kanban convertKanbanDTOToKanban(KanbanDTO kanbanDTO){
        Kanban kanban = new Kanban();
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
