package com.KotoPorot.Application_Demo.DTO;

public class SubscriberDTO {
    private String username;
    private Long userId;
    private String boardName;
    private Long boardId;

    public SubscriberDTO(String username, Long userId, String boardName, Long boardId) {
        this.username = username;
        this.userId = userId;
        this.boardName = boardName;
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
