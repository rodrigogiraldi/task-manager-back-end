package com.rodrigo.controllers;

import com.rodrigo.entities.User;
import com.rodrigo.utils.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserController userControllerMock;

    @Test
    public void createTest() {
        User user = generateUserEntity(1, "test@email.com", "123");

        when(userControllerMock.create(user)).thenReturn(new ResponseEntity<>(new Response("aSd1"), HttpStatus.CREATED));

        ResponseEntity<Response<String>> responseEntityWithToken = userControllerMock.create(user);

        assertNotNull(responseEntityWithToken);
        assertNotNull(responseEntityWithToken.getBody());

        Response<String> response = responseEntityWithToken.getBody();

        assertNotNull(response);
        assertTrue(response.getData().length() > 0);
    }

    private User generateUserEntity(long id, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    @Test
    public void logInTest() {
        User user = generateUserEntity(1, "test@email.com", "123");

        when(userControllerMock.logIn(user)).thenReturn(new ResponseEntity<>(new Response("aSd1"), HttpStatus.OK));

        ResponseEntity<Response<String>> responseEntityWithToken = userControllerMock.logIn(user);

        assertNotNull(responseEntityWithToken);
        assertNotNull(responseEntityWithToken.getBody());

        Response<String> response = responseEntityWithToken.getBody();

        assertNotNull(response);
        assertTrue(response.getData().length() > 0);
    }
}
