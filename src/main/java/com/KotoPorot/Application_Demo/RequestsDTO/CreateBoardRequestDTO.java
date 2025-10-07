package com.KotoPorot.Application_Demo.RequestsDTO;

public class CreateBoardRequestDTO {
    private String boardName; //Required from user


    public CreateBoardRequestDTO(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
