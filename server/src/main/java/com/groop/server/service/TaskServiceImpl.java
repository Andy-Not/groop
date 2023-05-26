package com.groop.server.service;
import com.groop.server.domain.Task;
import com.groop.server.dto.TaskDTO;
import com.groop.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class TaskServiceImpl implements  TaskService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task saveNewTask(TaskDTO taskDTO) {
        return taskRepository.save(convertTaskDTOtoTask(taskDTO));
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return task;
    }
}
