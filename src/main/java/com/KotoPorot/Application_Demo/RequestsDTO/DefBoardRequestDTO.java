package com.KotoPorot.Application_Demo.RequestsDTO;

public class DefBoardRequestDTO {
    private Long defaultBoardId;


    public DefBoardRequestDTO(Long defaultBoardId) {
        this.defaultBoardId = defaultBoardId;
    }

    public Long getDefaultBoardId() {
        return defaultBoardId;
    }

    public void setDefaultBoardId(Long defaultBoardId) {
        this.defaultBoardId = defaultBoardId;
    }
}
