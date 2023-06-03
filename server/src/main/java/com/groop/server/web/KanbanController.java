package com.groop.server.web;

import com.groop.server.domain.Kanban;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/kanban")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;

    @PostMapping("addKanban")
    ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO){
        try {
            return new ResponseEntity<>(kanbanService.saveNewKanban(kanbanDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    }

    @GetMapping("getAllKanban")
    ResponseEntity<?> getAllKanban(){
        try {
            return new ResponseEntity<>(kanbanService.findAllKanban(),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    };

    @PostMapping("addTaskToKanban/{kanban_id}")
    ResponseEntity<?> addNewTaskToKanbanById(@PathVariable Long kanban_id, @RequestBody TaskDTO taskDTO){
        Optional<Kanban> optKanban = kanbanService.findKanbanById(kanban_id);
        try {
            if (optKanban.isPresent()){
                return new ResponseEntity<>(kanbanService.addNewTaskToKanban(kanban_id,taskDTO), HttpStatus.ACCEPTED);
            }
            return noKanbanFound();
        }catch (Exception e){
            return errorMessage();
        }
    }

    @DeleteMapping("/{kanban_id}")
    public ResponseEntity<?> deleteKanban(@PathVariable Long kanban_id){
        try {
            Optional<Kanban> optionalKanban = kanbanService.findKanbanById(kanban_id);
            if (optionalKanban.isPresent()){
                kanbanService.deleteKanban(optionalKanban.get());
                return new ResponseEntity<String>("Kanban has been deleted", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<String>("Kanban was not found", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return errorMessage();
        }
    }
    ResponseEntity<?> errorMessage(){
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    ResponseEntity<?> noKanbanFound(){
        return new ResponseEntity<>("no kanban was found", HttpStatus.NOT_FOUND);
    }

}
