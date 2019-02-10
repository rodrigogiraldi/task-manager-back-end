package com.rodrigo.services;

import com.rodrigo.entities.User;
import com.rodrigo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createUser(User user) {

        List<User> usersFound = userRepository.findByEmail(user.getEmail());

        if (usersFound.size() == 0) {
            User userCreated = userRepository.save(user);

            String token = generateToken(userCreated.getId());

            return new ResponseEntity<String>(token, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Email already in use", HttpStatus.FORBIDDEN);
        }
    }

    /*
    Dummy token generator
     */
    private String generateToken(Long id) {
        String token = Base64.getEncoder().encodeToString(id.toString().getBytes());

        return token;
    }

    private Long decodeToken(String token) {
        String id = new String(Base64.getDecoder().decode(token));
        return Long.valueOf(id);
    }
}
