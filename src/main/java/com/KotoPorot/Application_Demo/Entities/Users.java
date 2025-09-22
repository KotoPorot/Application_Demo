package com.KotoPorot.Application_Demo.Entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;
    private Role role;

    @ManyToMany
            @JoinTable(
                    name = "boards_subs",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id"))
    List<Board> subscribe;


    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }


}
