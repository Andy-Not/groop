package com.groop.server.service;
import com.groop.server.model.Comment;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.model.Task;
import com.groop.server.dto.CommentDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.repository.KanbanSwimLaneRepository;
import com.groop.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    KanbanSwimLaneRepository kanbanSwimLaneRepository;


    public Task saveNewTask(TaskDTO taskDTO) {
        return taskRepository.save(convertTaskDTOtoTask(taskDTO));
    }

    public Task addCommentToTask(Long task_id, CommentDTO commentDTO) {
        Task task = taskRepository.findById(task_id).get();
//        task.addComment(convertCommentDtoToComment(commentDTO));
        return taskRepository.save(task);
    }


    public Comment convertCommentDtoToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage());
        return comment;
    }


    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Task convertTaskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
//        task.setTitle(taskDTO.getTitle());
//        task.setDescription(taskDTO.getDescription());
//        task.setStatus(taskDTO.getStatus());
        return task;
    }

    public Optional<Task> findTask(Long id){
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTaskKanbanColumn(long taskId, long kanbanId) {
        Optional<Task> task = taskRepository.findById(taskId);
        Optional<KanbanSwimLane> kanban = kanbanSwimLaneRepository.findById(kanbanId);
        if (task.isPresent() && kanban.isPresent()) {
//            task.get().setKanban(kanban.get());
            taskRepository.save(task.get());
        }
        return task;
    }

}
