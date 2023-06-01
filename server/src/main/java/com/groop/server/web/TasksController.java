package com.groop.server.web;

import com.groop.server.domain.Task;
import com.groop.server.dto.CommentDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/task")
public class TasksController {
    @Autowired
    private TaskService taskService;
    @PostMapping("addNewTask")
    ResponseEntity<?> createNewTask(@RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(taskService.saveNewTask(taskDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    }

    @PostMapping("addCommentToTask/{task_id}")
    public ResponseEntity<?> addCommentToTaskById(@PathVariable Long task_id, @RequestBody CommentDTO commentDTO){
        Optional<Task> task = taskService.findTask(task_id);
        try {
            if (task.isPresent()){
                taskService.addCommentToTask(task_id, commentDTO);
                return new ResponseEntity<>("Comment was posted", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("task does not exist", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return errorMessage();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        Optional<Task> optionalTask = taskService.findTask(id);
        try {
            if (optionalTask.isPresent()){
                taskService.deleteTask(optionalTask.get());
                return new ResponseEntity<>("Task has been deleted",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Task does not exist",HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return errorMessage();
        }
    }

    ResponseEntity<?> errorMessage(){
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
