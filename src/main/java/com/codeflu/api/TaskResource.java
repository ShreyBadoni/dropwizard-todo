package com.codeflu.api;

import com.codeflu.models.Task;
import com.codeflu.services.TaskService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/tasks") // Base path for this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;

    // Constructor
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Get all tasks
    @GET
    public Response getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return Response.ok(tasks).build();
    }

    // 2. Get a task by ID
    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") UUID id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return Response.ok(task.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // 3. Create a new task
    @POST
    public Response createTask(TaskRequest request) {
        Task task = taskService.createTask(
                request.getDescription(),
                request.getStartDate(),
                request.getTargetDate()
        );
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    // 4. Update an existing task
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") UUID id, TaskRequest request) {
        Optional<Task> updatedTask = taskService.updateTask(
                id,
                request.getDescription(),
                request.getStartDate(),
                request.getTargetDate(),
                request.getStatus()
        );

        if (updatedTask.isPresent()) {
            return Response.ok(updatedTask.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // 5. Delete a task
    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") UUID id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Inner class for handling task requests
    public static class TaskRequest {
        private String description;
        private LocalDate startDate;
        private LocalDate targetDate;
        private Task.Status status;

        // Getters and Setters
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getTargetDate() {
            return targetDate;
        }

        public void setTargetDate(LocalDate targetDate) {
            this.targetDate = targetDate;
        }

        public Task.Status getStatus() {
            return status;
        }

        public void setStatus(Task.Status status) {
            this.status = status;
        }
    }
}
