package com.KotoPorot.Application_Demo.RequestsDTO;

public class CreateBoardDTO {
    private String boardName;

    public CreateBoardDTO(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
