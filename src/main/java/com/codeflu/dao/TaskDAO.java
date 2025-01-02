package com.codeflu.dao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.codeflu.models.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // Create a new Task
    public Task create(Task task) {
        return persist(task); // Save or update the task in the database
    }

    // Retrieve all tasks
    public List<Task> findAll() {
        return currentSession().createQuery("FROM Task", Task.class).getResultList();
    }

    // Retrieve a task by ID
    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    // Delete a task
    public void delete(Task task) {
        currentSession().delete(task);
    }
}
