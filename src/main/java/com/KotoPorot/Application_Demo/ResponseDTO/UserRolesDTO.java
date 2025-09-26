package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.UsersRoles;
import com.KotoPorot.Application_Demo.Enums.BoardRole;

public class UserRolesDTO {
    private String boardName;
    private Long boardId;
    private String userName;
    private Long userId;
    private BoardRole boardRole;

    public UserRolesDTO(UsersRoles usersRoles) {
        this.boardName = usersRoles.getBoard().getName();
        this.boardId = usersRoles.getBoard().getId();
        this.userName = usersRoles.getUser().getUsername();
        this.userId = usersRoles.getUser().getId();
        this.boardRole = usersRoles.getBoardRole();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BoardRole getBoardRole() {
        return boardRole;
    }

    public void setBoardRole(BoardRole boardRole) {
        this.boardRole = boardRole;
    }

    @Override
    public String toString() {
        return "UserRolesDTO{" +
                "boardName='" + boardName + '\'' +
                ", boardId=" + boardId +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", boardRole=" + boardRole +
                '}';
    }
}
