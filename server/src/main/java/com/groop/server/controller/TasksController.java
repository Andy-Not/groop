package com.groop.server.controller;

import com.groop.server.model.KanbanSwimLane;
import com.groop.server.model.Task;
import com.groop.server.dto.CommentDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.service.SwimLaneService;
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

    @Autowired
    private SwimLaneService swimLaneService;
    @PostMapping("addNewTask/{swimLaneID}")
    ResponseEntity<?> createNewTask(@RequestBody TaskDTO taskDTO, @PathVariable Long swimLaneID){
        Optional<KanbanSwimLane> optionalKanbanSwimLane = swimLaneService.findSwimLaneById(swimLaneID);
        try {
            if (optionalKanbanSwimLane.isPresent()){
                return new ResponseEntity<>(taskService.saveNewTask(optionalKanbanSwimLane.get() ,taskDTO), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("swim lane does not exist", HttpStatus.NOT_FOUND);
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
