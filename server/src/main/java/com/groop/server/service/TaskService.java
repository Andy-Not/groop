package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.domain.Task;
import com.groop.server.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface TaskService {
    Task saveNewTask(TaskDTO taskDTO);

    void  deleteTask(Task task);

    Optional<Task> findTask(Long id);

}
