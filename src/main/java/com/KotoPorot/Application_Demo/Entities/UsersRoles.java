package com.KotoPorot.Application_Demo.Entities;

import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "board_id"})})
public class UsersRoles {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardRole boardRole;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "subs")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @JsonBackReference(value = "board_subs")
    private Board board;


    public UsersRoles(Users user, Board board, BoardRole boardRole) {
        this.user = user;
        this.board = board;
        this.boardRole = boardRole;
    }

    public UsersRoles() {
    }

    public UsersRoles(Long id, Users user, Board board, BoardRole boardRole) {
        this.user = user;
        this.board = board;
        this.boardRole = boardRole;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BoardRole getBoardRole() {
        return boardRole;
    }

    public void setBoardRole(BoardRole boardRole) {
        this.boardRole = boardRole;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsersRoles that = (UsersRoles) o;
        return Objects.equals(id, that.id) && boardRole == that.boardRole && Objects.equals(user, that.user) && Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boardRole, user, board);
    }
}
