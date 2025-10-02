package com.KotoPorot.Application_Demo.RequestsDTO;

public class DeleteBoardMemberRequestDTO {
    private Long boardId;   //Required from front
    private Long userId;    //Required from front

    public DeleteBoardMemberRequestDTO() {
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
