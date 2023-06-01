package com.groop.server.web;

import com.groop.server.domain.Comment;
import com.groop.server.domain.User;
import com.groop.server.dto.AuthCredentialsRequest;
import com.groop.server.service.CommentService;
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
    private CommentService commentService;

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
    @DeleteMapping("deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
            Optional<Comment> optionalComment = commentService.findCommentById(id);
            try {
                if (optionalComment.isPresent()){
                    commentService.deleteComment(optionalComment.get());
                    return new ResponseEntity<>("the comment has been deleted", HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>("comment does not exist", HttpStatus.BAD_GATEWAY);
            }catch (Exception e){
                return errorMessage();
            }
    }
    ResponseEntity<?> errorMessage(){
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
