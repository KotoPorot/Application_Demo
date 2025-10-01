package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.UsersRoles;
import com.KotoPorot.Application_Demo.Enums.BoardRole;

public class BoardMemberDTO {
    private Long userId;
    private String username;
    private BoardRole boardRole;

    public BoardMemberDTO(UsersRoles role) {
        this.userId = role.getUser().getId();
        this.username = role.getUser().getUsername();
        this.boardRole = role.getBoardRole();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BoardRole getBoardRole() {
        return boardRole;
    }

    public void setBoardRole(BoardRole boardRole) {
        this.boardRole = boardRole;
    }

}
