package com.groop.server.service;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.model.Task;
import com.groop.server.dto.TaskDTO;
import com.groop.server.model.TaskStatus;
import com.groop.server.repository.TaskRepository;
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
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskDTO saveNewTask(KanbanSwimLane swimLane,TaskDTO taskDTO) {
        return convertTaskToDTO(taskRepository.save(convertTaskDTOtoTask(taskDTO, swimLane)));
    }

    public TaskDTO moveTask(Task task, KanbanSwimLane swimLane){
        task.setKanbanSwimLane(swimLane);
        taskRepository.save(task);
        return convertTaskToDTO(task);
    }

    public List<TaskDTO> findAllTasksBySwimLaneId(Long swimLaneId){
        List<Task> allTasks = taskRepository.findAll();
        List<TaskDTO> allTasksDto = new ArrayList<>();

        for (Task task : allTasks) {
            if (Objects.equals(task.getKanbanSwimLane().getId(), swimLaneId)) {
                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setId(task.getId());
                taskDTO.setTitle(task.getTitle());
                taskDTO.setDescription(task.getDescription());
                allTasksDto.add(taskDTO);
            }
        }
        return allTasksDto;
    }
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void  deleteALlTasksInKanban(Long kanbanId){
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            if (Objects.equals(task.getKanbanSwimLane().getKanban().getId(), kanbanId)){
                taskRepository.delete(task);
            }
        }
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO, KanbanSwimLane swimLane){
        Task task = new Task();
        //hard coded at the moment
        TaskStatus currentStatus = TaskStatus.TODO;
        task.setKanbanSwimLane(swimLane);
        task.setDescription(taskDTO.getDescription());
        task.setTaskOrder(1);
        task.setStatus(currentStatus);
        task.setTitle(taskDTO.getTitle());
        return task;
    }

    public TaskDTO convertTaskToDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(TaskStatus.DONE);
        taskDTO.setTitle(task.getTitle());
        taskDTO.setSwimLaneID(task.getKanbanSwimLane().getId());
        return taskDTO;
    }



    public Optional<Task> findTask(Long id){
        return taskRepository.findById(id);
    }

}
