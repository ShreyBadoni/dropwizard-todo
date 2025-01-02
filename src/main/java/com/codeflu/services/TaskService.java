package com.codeflu.services;

import com.codeflu.models.Task;
import com.codeflu.dao.TaskDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    public Task createTask(String description, LocalDate startDate, LocalDate targetDate, Task.Status status) {
        Task task = new Task(description, startDate, targetDate, status);
        return taskDAO.create(task);
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskDAO.findById(id);
    }

    public boolean deleteTask(UUID id) {
        Optional<Task> task = taskDAO.findById(id);
        if (task.isPresent()) {
            taskDAO.delete(task.get());
            return true;
        }
        return false;
    }
}
