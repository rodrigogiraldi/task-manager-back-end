package com.rodrigo.controllers;

import com.rodrigo.entities.User;
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
    public void testCreate() {
        User user = generateUserEntity(1, "test@email.com", "123");

        when(userControllerMock.create(user)).thenReturn(new ResponseEntity<>("aSd1", HttpStatus.CREATED));

        ResponseEntity<String> responseEntityWithToken = userControllerMock.create(user);

        assertNotNull(responseEntityWithToken);
        assertNotNull(responseEntityWithToken.getBody());
        assertTrue(responseEntityWithToken.getBody().length() > 0);
    }

    private User generateUserEntity(long id, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
