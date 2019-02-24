package com.rodrigo.services;

import com.rodrigo.entities.User;
import com.rodrigo.repositories.UserRepository;
import com.rodrigo.utils.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;

    private static final String USER_EMAIL_IN_USE = "already.used@email.com";
    private static final String USER_EMAIL_VALID = "new@email.com";
    private static final String USER_PASSWORD = "123";
    private static final Long ID = 5L;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void createUserWithValidEmailTest() {
        User user = new User();
        user.setEmail(USER_EMAIL_VALID);
        user.setPassword(USER_PASSWORD);

        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByEmail(USER_EMAIL_VALID)).thenReturn(Arrays.asList(new User[]{}));
        when(userRepositoryMock.save(user)).thenReturn(user);

        userService.withUserRepository(userRepositoryMock);

        ResponseEntity<Response<String>> responseEntity = userService.createUser(user);

        assertNotNull(responseEntity);

        Response<String> response = responseEntity.getBody();

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void createUserWithEmailInUseTest() {
        User user = new User();
        user.setEmail(USER_EMAIL_IN_USE);

        List<User> userList = Arrays.asList(new User[]{user});

        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByEmail(USER_EMAIL_IN_USE)).thenReturn(userList);

        userService.withUserRepository(userRepositoryMock);

        ResponseEntity<Response<String>> responseEntity = userService.createUser(user);

        assertNotNull(responseEntity);

        Response<String> response = responseEntity.getBody();

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void generateTokenTest() {
        String token = userService.generateToken(Long.valueOf(1));

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void decodeTokenTest() {
        String tokenEncoded = userService.generateToken(ID);
        Long tokenDecoded = userService.decodeToken(tokenEncoded);

        assertNotNull(tokenDecoded);
        assertEquals(ID, tokenDecoded);
    }

    @Test
    public void logInWhenUserIsFoundTest() {
        User user = new User();
        user.setEmail(USER_EMAIL_IN_USE);
        user.setPassword(USER_PASSWORD);

        List<User> userList = Arrays.asList(new User[]{user});

        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByEmailAndPassword(USER_EMAIL_IN_USE, USER_PASSWORD)).thenReturn(userList);

        userService.withUserRepository(userRepositoryMock);

        ResponseEntity<Response<String>> responseEntity = userService.logIn(user);

        assertNotNull(responseEntity);

        Response<String> response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(response.getData());
        assertNotEquals("", response.getData());
    }

    @Test
    public void logInWhenUserIsNotFoundTest() {
        User user = new User();
        user.setEmail(USER_EMAIL_IN_USE);
        user.setPassword(USER_PASSWORD);

        List<User> userList = Arrays.asList(new User[]{user});

        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByEmailAndPassword(USER_EMAIL_IN_USE, USER_PASSWORD)).thenReturn(Arrays.asList(new User[]{}));

        userService.withUserRepository(userRepositoryMock);

        ResponseEntity<Response<String>> responseEntity = userService.logIn(user);

        assertNotNull(responseEntity);

        Response<String> response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(response.getData());
        assertEquals("", response.getData());
    }
}
