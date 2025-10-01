package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Board;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BoardDTO {
    private Long boardId;
    private String boardName;

    private List<BoardMemberDTO> members;


    public BoardDTO(Board board) {
        this.boardId = board.getId();
        this.boardName = board.getName();
        this.members = board.getMembers().stream().map(BoardMemberDTO::new).collect(Collectors.toList());
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<BoardMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<BoardMemberDTO> members) {
        this.members = members;
    }
}
