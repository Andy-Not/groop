package com.groop.server.controller;

import com.groop.server.dto.SwimLaneDTO;
import com.groop.server.dto.UserDTO;
import com.groop.server.model.Kanban;
import com.groop.server.model.KanbanSwimLane;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.model.User;
import com.groop.server.repository.KanbanRepository;
import com.groop.server.repository.UserRepository;
import com.groop.server.service.KanbanService;
import com.groop.server.service.SwimLaneService;
import com.groop.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/kanban")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;

    @Autowired
    KanbanRepository kanbanRepository;

    @Autowired
    private UserRepository userService;

    @Autowired
    private SwimLaneService swimLaneService;

    @Autowired
    private TaskService taskService;

    @PostMapping("createKanban")
    ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO){
        Optional<User> optionalUser = userService.findById(kanbanDTO.getOwner_id());
        String[] defaultTasks = {"TODO", "IN-PROGRESS", "DONE"};
        List<SwimLaneDTO> swimLanes = new ArrayList<>();
        try {
            if (optionalUser.isPresent()){
               KanbanDTO response = kanbanService.saveNewKanban(kanbanDTO, optionalUser.get());
                for (String title : defaultTasks) {
                   swimLanes.add(swimLaneService.createSwimLane(kanbanService.findKanbanById(response.getId()).get(), title));
                }
                response.setSwimLanes(swimLanes);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("user does not exist", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return errorMessage();
        }
    }
    @PostMapping("addUser/{kanban_id}/{user_id}")
    ResponseEntity<?> addUserToKanban(@PathVariable Long user_id,@PathVariable Long kanban_id){
        Optional<User> optionalUser = userService.findById(user_id);
        Optional<Kanban> optionalKanban = kanbanRepository.findById(kanban_id);
        try {
            if (optionalUser.isPresent() && optionalKanban.isPresent()){
                if (optionalKanban.get().getUsers().contains(optionalUser.get())){
                    return new ResponseEntity<>("user already exist", HttpStatus.BAD_REQUEST);
                }
                kanbanService.addUserToKanbanById(optionalUser.get(), optionalKanban.get());
                return new ResponseEntity<>("user " + optionalUser.get().getUsername() + " has been added",
                        HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("This user or kanban does not exist", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("could not add user", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("createNewTask/{swimLaneID}")
    ResponseEntity<?> addNewTaskToKanbanById(@PathVariable Long swimLaneID, @RequestBody TaskDTO taskDTO){
        Optional<KanbanSwimLane> optionalSwimLane = swimLaneService.findSwimLaneById(swimLaneID);
        try {
            if (optionalSwimLane.isPresent()){
                return new ResponseEntity<>(kanbanService.addNewTaskToKanban(swimLaneID,taskDTO), HttpStatus.ACCEPTED);
            }
            return noKanbanFound();
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
    }

    @GetMapping("getSwimLaneIn/{kanban_id}")
    ResponseEntity<?> getSwimLaneInKanban(@PathVariable Long kanban_id){
        Optional<Kanban> optionalKanban = kanbanService.findKanbanById(kanban_id);
        try {
            if (optionalKanban.isPresent()){
                return new ResponseEntity<>(swimLaneService.findAllSwimLanesByKanbanId(kanban_id),HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("SwimLane with id of " + kanban_id + " does not exist",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    }
    @GetMapping("AllUsersInKanban/{kanban_id}")
    ResponseEntity<?> getAllUsersInKanban(@PathVariable Long kanban_id){
        Optional<Kanban> optionalKanban = kanbanService.findKanbanById(kanban_id);
        List<UserDTO> usersList = new ArrayList<>();
        try {
            if (optionalKanban.isPresent()){
               List<User> users = optionalKanban.get().getUsers();
                for (User user : users) {
                    UserDTO userInKanban = new UserDTO();
                    userInKanban.setId(user.getId());
                    userInKanban.setUsername(user.getUsername());
                    usersList.add(userInKanban);
                }
                return new ResponseEntity<>(usersList, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Kanban does not exist", HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            return errorMessage();
        }

    }

    @DeleteMapping("/{kanban_id}")
    public ResponseEntity<?> deleteKanban(@PathVariable Long kanban_id){
        try {
            Optional<Kanban> optionalKanban = kanbanService.findKanbanById(kanban_id);
            if (optionalKanban.isPresent()){
                taskService.deleteALlTasksInKanban(kanban_id);
                swimLaneService.deleteSwimLanesInKanban(kanban_id);
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
