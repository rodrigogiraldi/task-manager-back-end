package com.rodrigo.repositories;

import com.rodrigo.entities.Task;
import com.rodrigo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findFirstByUser(User user);
}
