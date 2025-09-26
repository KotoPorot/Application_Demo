package com.KotoPorot.Application_Demo.Entities;


import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.KotoPorot.Application_Demo.Enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "subs")
    private List<UsersRoles> roles = new ArrayList<>();


    @ManyToMany(mappedBy = "members")
    @JsonManagedReference
    private List<Department> departments;

    @OneToMany(mappedBy = "executor")
    @JsonManagedReference
    private List<Task> userTasks;

    @OneToOne
    @JoinColumn(name = "respDep_id")
    private Department respDep;


    public Users() {
    }

    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UsersRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UsersRoles> roles) {
        this.roles = roles;
    }
    public Role getRole() {
        return Role.ADMIN;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", departments=" + departments +
                ", userTasks=" + userTasks +
                ", respDep=" + respDep +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(roles, users.roles) && Objects.equals(departments, users.departments) && Objects.equals(userTasks, users.userTasks) && Objects.equals(respDep, users.respDep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles, departments, userTasks, respDep);
    }
}
