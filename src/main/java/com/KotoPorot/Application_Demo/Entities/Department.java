package com.KotoPorot.Application_Demo.Entities;

import com.KotoPorot.Application_Demo.RequestsDTO.CreateDepRequestDTO;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Users responsibleManager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "department_members",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"),
            uniqueConstraints= @UniqueConstraint(columnNames = {"department_id", "users_id"})
    )
    @JsonManagedReference
    private List<Users> members = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, orphanRemoval = true)
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

    public void setId(long id) {
        this.id = id;
    }

    public void setResponsibleManager(Users responsibleManager) {
        this.responsibleManager = responsibleManager;
    }

    public Department(CreateDepRequestDTO depDTO, Board board) {
        this.name = depDTO.getName();
        this.board = board;
    }

    public Department() {
    }
}
