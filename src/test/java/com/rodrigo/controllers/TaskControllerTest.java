package com.rodrigo.controllers;

import com.rodrigo.entities.Task;
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
public class TaskControllerTest {

    @Mock
    private TaskController taskControllerMock;

    @Test
    public void createTest() {

        Task task = new Task();
        task.setId(0);
        task.setUser(new User());
        task.getUser().setId(0);

        String authorizationToken = "aSd1";

        Task taskResponse = new Task();
        taskResponse.setId(1);
        taskResponse.setUser(new User());
        taskResponse.getUser().setId(1);

        when(taskControllerMock.create(task, authorizationToken)).thenReturn(new ResponseEntity<>(new Response(taskResponse), HttpStatus.CREATED));

        ResponseEntity<Response<Task>> responseEntity = taskControllerMock.create(task, authorizationToken);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());

        Response<Task> response = responseEntity.getBody();

        assertNotNull(response);
        assertNotNull(response.getData());
        assertTrue(taskResponse.getId() != 0);
        assertNotNull(response.getData().getUser());
        assertTrue(response.getData().getUser().getId() != 0);
    }
}
