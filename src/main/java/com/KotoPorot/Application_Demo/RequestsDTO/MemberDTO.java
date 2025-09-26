package com.KotoPorot.Application_Demo.RequestsDTO;

public class MemberDTO {
    private String boardName;
    private String userName;

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MemberDTO(String boardName, String userName) {
        this.boardName = boardName;
        this.userName = userName;
    }

    public MemberDTO() {
    }
}
