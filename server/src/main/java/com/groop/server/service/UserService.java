package com.groop.server.service;

import com.groop.server.dto.UserDTO;
import com.groop.server.model.User;
import com.groop.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author joandy alejo garcia
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
    public boolean isUsernameValid(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isEmpty();
    }

    public boolean isPasswordValid(String password) {
        int minLength = 6;

        if (password.length() < minLength) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return false;
        }

        return true;
    }

    public void createNewUser(String username, String password){
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
