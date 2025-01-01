package com.codeflu.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public class Task {

    private final UUID id; // Unique Task ID

    @NotNull
    @Size(min = 1, message = "Description cannot be empty")
    private String description; // Task description

    @NotNull
    private LocalDate startDate; // Start date of the task

    @NotNull
    private LocalDate targetDate; // Target completion date

    @NotNull
    private Status status; // Status of the task (TODO, WIP, DONE)

    // Constructor
    public Task(String description, LocalDate startDate, LocalDate targetDate, Status status) {
        this.id = UUID.randomUUID(); // Generate a unique ID
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.status = status;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Enum for Task Status
    public enum Status {
        TODO,
        WIP,
        DONE
    }
}
