package com.rodrigo.services;

import com.rodrigo.entities.User;
import com.rodrigo.repositories.UserRepository;
import com.rodrigo.utils.Response;
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

    public ResponseEntity<Response<String>> createUser(User user) {

        List<User> usersFound = userRepository.findByEmail(user.getEmail());

        Response<String> response = new Response<>();

        if (usersFound.size() == 0) {
            User userCreated = userRepository.save(user);

            String token = generateToken(userCreated.getId());
            response.setData(token);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setData("Email already in use");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /*
    Dummy token generator
     */
    protected String generateToken(Long id) {
        String token = Base64.getEncoder().encodeToString(id.toString().getBytes());

        return token;
    }

    protected Long decodeToken(String token) {
        String id = new String(Base64.getDecoder().decode(token));
        return Long.valueOf(id);
    }

    public void withUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
