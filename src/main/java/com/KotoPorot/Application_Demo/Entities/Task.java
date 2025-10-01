package com.KotoPorot.Application_Demo.Entities;

import com.KotoPorot.Application_Demo.Enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime createdDate;
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="department_id")
    @JsonBackReference
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    @JsonBackReference
    private Users executor;

    public Task() {
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Users getExecutor() {
        return executor;
    }

    public void setExecutor(Users executor) {
        this.executor = executor;
    }

    public void setId(long id) {
        this.id = id;
    }

}
