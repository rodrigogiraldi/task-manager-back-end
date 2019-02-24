package com.rodrigo.controllers;

import com.rodrigo.entities.Task;
import com.rodrigo.services.TaskService;
import com.rodrigo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public ResponseEntity<Response<Task>> create(@RequestBody Task task, @RequestHeader(value = "Authorization") String authorizationToken) {
        return taskService.createTask(task, authorizationToken);
    }
}
