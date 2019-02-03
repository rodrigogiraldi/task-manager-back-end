package com.rodrigo.services;

import com.rodrigo.entities.User;
import com.rodrigo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {

        List<User> usersFound = userRepository.findByEmail(user.getEmail());

        if (usersFound.size() == 0) {
            User userCreated = userRepository.save(user);

            return generateToken(userCreated.getId());
        } else {
            return "Email already in use";
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
