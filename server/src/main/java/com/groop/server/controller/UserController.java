package com.groop.server.controller;

import com.groop.server.dto.SignUpDTO;
import com.groop.server.dto.UserDTO;
import com.groop.server.model.User;
import com.groop.server.repository.UserRepository;
import com.groop.server.service.UserService;
import com.groop.server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/findUser")
    public ResponseEntity<?> findUserByJWT(@RequestParam String token){
        String username = JwtUtil.getUsernameFromToken(token);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent())
            return new ResponseEntity<>(userService.userToDTO(optionalUser.get()),HttpStatus.OK);
        return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/newUser")
    public ResponseEntity<?> createNewUser(@RequestBody SignUpDTO signUp){
        String username = signUp.getUsername();
        String password = signUp.getPassword();

        if (!userService.isUsernameValid(username))
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);

        if (!userService.isPasswordValid(password))
            return new ResponseEntity<>("password must be at least 6 digits, must have a special character and a uppercase letter and a lowercase", HttpStatus.BAD_REQUEST);


        userService.createNewUser(username, password);
        return new ResponseEntity<>("new user has been created", HttpStatus.ACCEPTED);
    }
}
