package com.KotoPorot.Application_Demo.RequestsDTO;

public class CreateDepRequestDTO {
    private Long boardId;   //Required from front
    private String name;    //Required from user

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public CreateDepRequestDTO() {
    }
}
