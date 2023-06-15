package com.groop.server.service;

import com.groop.server.domain.Comment;
import com.groop.server.domain.Task;
import com.groop.server.dto.CommentDTO;
import com.groop.server.dto.TaskDTO;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
public interface TaskService {
    Task saveNewTask(TaskDTO taskDTO);

    Task addCommentToTask(Long task_id, CommentDTO commentDTO);

    Comment convertCommentDtoToComment(CommentDTO commentDTO);

    void  deleteTask(Task task);

    Optional<Task> findTask(Long id);

    Optional<Task> updateTaskKanbanColumn(long task_id, long kanban);

}
