package com.KotoPorot.Application_Demo.RequestsDTO;

import com.KotoPorot.Application_Demo.Enums.BoardRole;

public class AddBoardMemberRequestDTO {
    private Long boardId;   //Required from front
    private String userName;    //Required from user

    private BoardRole boardRole;    //Possible from user (Enums:MEMBER, MANAGER), as default "MEMBER"


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

    public BoardRole getBoardRole() {
        return boardRole;
    }

    public void setBoardRole(BoardRole boardRole) {
        this.boardRole = boardRole;
    }

    public AddBoardMemberRequestDTO() {
    }
}
