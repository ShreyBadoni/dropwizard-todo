package com.codeflu.services;

import com.codeflu.models.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    // In-memory task storage (replaceable with a database later)
    private final Map<UUID, Task> taskMap = new HashMap<>();

    // Create a new task
    public Task createTask(String description, LocalDate startDate, LocalDate targetDate) {
        Task task = new Task(description, startDate, targetDate, Task.Status.TODO);
        taskMap.put(task.getId(), task);
        return task;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    // Get a task by ID
    public Optional<Task> getTaskById(UUID id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    // Update a task
    public Optional<Task> updateTask(UUID id, String description, LocalDate startDate, LocalDate targetDate, Task.Status status) {
        if (!taskMap.containsKey(id)) {
            return Optional.empty();
        }
        Task task = taskMap.get(id);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setTargetDate(targetDate);
        task.setStatus(status);
        return Optional.of(task);
    }

    // Delete a task
    public boolean deleteTask(UUID id) {
        if (!taskMap.containsKey(id)) {
            return false;
        }
        taskMap.remove(id);
        return true;
    }
}
