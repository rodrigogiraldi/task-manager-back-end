package com.rodrigo.services;

import com.rodrigo.entities.Task;
import com.rodrigo.entities.User;
import com.rodrigo.repositories.TaskRepository;
import com.rodrigo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserService userService;

	public ResponseEntity<Response<Task>> createTask(Task task, String authorizationToken) {

		long userId = userService.decodeToken(authorizationToken);

		User user = new User();
		user.setId(userId);

		task.setUser(user);

		Task taskDb = taskRepository.save(task);

		Response<Task> response = new Response<>();
		response.setData(taskDb);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<List<Task>>> getAll(String authorizationToken) {

		long userId = userService.decodeToken(authorizationToken);

		User user = new User();
		user.setId(userId);

		List<Task> tasks = taskRepository.findByUser(user);

		Response<List<Task>> response = new Response<>();
		response.setData(tasks);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Response<Boolean>> hasAny(String authorizationToken) {
		long userId = userService.decodeToken(authorizationToken);

		User user = new User();
		user.setId(userId);

		List<Task> tasks = taskRepository.findFirstByUser(user);

		Response<Boolean> response = new Response<>();
		response.setData(tasks.size() > 0);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
