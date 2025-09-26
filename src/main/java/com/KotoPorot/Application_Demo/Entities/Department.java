package com.KotoPorot.Application_Demo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Users responsibleManager;

    @OneToMany
    @JoinTable(
            name = "department_members",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    @JsonBackReference
    private List<Users> members = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Task> departmentTasks = new ArrayList<>();

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

    public List<Users> getMembers() {
        return members;
    }

    public void setMembers(List<Users> members) {
        this.members = members;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Task> getDepartmentTasks() {
        return departmentTasks;
    }

    public void setDepartmentTasks(List<Task> departmentTasks) {
        this.departmentTasks = departmentTasks;
    }

    public Users getResponsibleManager() {
        return responsibleManager;
    }

    public void setResponsibleManager(Users responsibleManager) {
        this.responsibleManager = responsibleManager;
    }

    public Department() {
    }
}
