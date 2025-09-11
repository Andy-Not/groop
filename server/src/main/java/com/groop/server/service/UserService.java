package com.groop.server.service;

import com.groop.server.dto.UserDTO;
import com.groop.server.model.User;
import com.groop.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private static final int MIN_PASSWORD_LENGTH = 6;


    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
    public boolean isUsernameAvailable(String username){
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isEmailInUse(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }

    public UserDTO createNewUser(String email,String username, String password){
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        user.setRole("member");
        userRepository.save(user);
        return userToDTO(savedUser);
    }
}
