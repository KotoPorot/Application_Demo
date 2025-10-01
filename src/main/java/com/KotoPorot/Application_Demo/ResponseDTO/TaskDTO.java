package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Department;
import com.KotoPorot.Application_Demo.Entities.Task;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private TaskStatus taskStatus;
    private Long departmentId;
    private Long executorId;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.createdDate = task.getCreatedDate();
        this.taskStatus = task.getTaskStatus();

        if (task.getDepartment() != null) {
            this.departmentId = task.getDepartment().getId();
        } else {
            this.departmentId = null;
        }

        if (task.getExecutor() != null) {
            this.executorId = task.getExecutor().getId();
        } else {
            this.executorId = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public TaskDTO() {
    }


}
