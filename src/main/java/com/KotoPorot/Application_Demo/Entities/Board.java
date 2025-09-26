package com.KotoPorot.Application_Demo.Entities;

import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String owner;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "board_subs")
    private List<UsersRoles> members = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @JsonManagedReference
    private List<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @JsonManagedReference
    private List<Task> boardTasks = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<UsersRoles> getMembers() {
        return members;
    }

    public void setMembers(List<UsersRoles> members) {
        this.members = members;
    }

    public UsersRoles setUsersRoles(Users user, BoardRole role){
       UsersRoles existUserRole = members.stream().filter(usersRoles -> usersRoles.getUser().equals(user))
               .findAny()
               .orElse(null);
       if(existUserRole==null){
           UsersRoles usersRole = new UsersRoles(user, this, role);
           members.add(usersRole);
           if (user.getRoles()==null){
               user.setRoles(new ArrayList<>());
           }
           user.getRoles().add(usersRole);
           return usersRole;
       }else {
           existUserRole.setBoardRole(role);
           return existUserRole;
       }

    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", members=" + members +
                ", departments=" + departments +
                ", boardTasks=" + boardTasks +
                '}';
    }
}
