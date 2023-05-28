package com.groop.server.service;

import com.groop.server.domain.Kanban;
import com.groop.server.domain.Task;
import com.groop.server.dto.TaskDTO;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
public interface TaskService {
    Task saveNewTask(TaskDTO taskDTO);

}
