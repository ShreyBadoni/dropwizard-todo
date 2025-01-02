package com.codeflu.api;

import com.codeflu.models.Task;
import com.codeflu.services.TaskService;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @UnitOfWork // Starts a transaction for this method
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @POST
    @UnitOfWork // Starts a transaction for this method
    public Task createTask(Task task) {
        return taskService.createTask(
                task.getDescription(),
                task.getStartDate(),
                task.getTargetDate(),
                task.getStatus() != null ? task.getStatus() : Task.Status.TODO
        );
    }

    @GET
    @Path("/{id}")
    @UnitOfWork // Starts a transaction for this method
    public Response getTaskById(@PathParam("id") UUID id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(Response::ok).orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork // Starts a transaction for this method
    public Response deleteTask(@PathParam("id") UUID id) {
        if (taskService.deleteTask(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
