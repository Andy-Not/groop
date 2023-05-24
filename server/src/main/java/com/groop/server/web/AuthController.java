package com.groop.server.web;

import com.groop.server.domain.User;
import com.groop.server.dto.AuthCredentialsRequest;
import com.groop.server.dto.KanbanDTO;
import com.groop.server.service.KanbanService;
import com.groop.server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KanbanService kanbanService;

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
    @PostMapping("/addKanban")
    ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO){
        try {
            return new ResponseEntity<>(kanbanService.saveNewKanban(kanbanDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
