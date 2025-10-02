package com.KotoPorot.Application_Demo.RequestsDTO;

public class AddDepMemberRequestDTO {

    private Long boardId;   //Required from front
    private Long departmentId;  //Required from front
    private Long userId;    //Required from user, user should be board member!


    public AddDepMemberRequestDTO() {
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
