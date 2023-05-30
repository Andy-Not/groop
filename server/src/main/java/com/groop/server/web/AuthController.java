package com.groop.server.web;

import com.groop.server.domain.Kanban;
import com.groop.server.domain.User;
import com.groop.server.dto.AuthCredentialsRequest;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.dto.TaskDTO;
import com.groop.server.service.KanbanService;
import com.groop.server.service.TaskService;
import com.groop.server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KanbanService kanbanService;

    @Autowired
    private TaskService taskService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> login (@RequestBody AuthCredentialsRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(user)
                    )
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("addKanban")
    ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO){
        try {
            return new ResponseEntity<>(kanbanService.saveNewKanban(kanbanDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    }

    @PostMapping("addNewTask")
    ResponseEntity<?> createNewTask(@RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(taskService.saveNewTask(taskDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return errorMessage();
        }
    }

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
    @DeleteMapping("deleteKanban/{kanban_id}")
    public ResponseEntity<?> deleteKanban(@PathVariable Long kanban_id){
        try {
            Optional<Kanban> optionalKanban = kanbanService.findKanbanById(kanban_id);
            if (optionalKanban.isPresent()){
                kanbanService.deleteKanban(optionalKanban.get());
                return new ResponseEntity<String>("Kanban has been deletes", HttpStatus.ACCEPTED);
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
