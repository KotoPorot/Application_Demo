package com.KotoPorot.Application_Demo.RequestsDTO;

public class DeleteDepMemberDTO { //Required from front
    private Long departmentId;  //Required from front
    private Long userId;    //Required from user


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

    public DeleteDepMemberDTO() {
    }
}
