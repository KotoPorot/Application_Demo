package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Board;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerBoardDTO extends BoardDTO {
    private List<DepartmentDTO> boardDepartments;
    private List<TaskDTO> boardTasks;


    public OwnerBoardDTO(Board board) {
        super(board);
        if (board.getDepartments()!=null) {
            this.boardDepartments = board.getDepartments().stream().map(DepartmentDTO::new).collect(Collectors.toList());
        }
        if (board.getBoardTasks()!=null){
        this.boardTasks=board.getBoardTasks().stream().map(TaskDTO::new).collect(Collectors.toList());
        }
    }

    public List<DepartmentDTO> getBoardDepartments() {
        return boardDepartments;
    }

    public void setBoardDepartments(List<DepartmentDTO> boardDepartments) {
        this.boardDepartments = boardDepartments;
    }

    public List<TaskDTO> getBoardTasks() {
        return boardTasks;
    }

    public void setBoardTasks(List<TaskDTO> boardTasks) {
        this.boardTasks = boardTasks;
    }

    @Override
    public String toString() {
        return "OwnerBoardDTO{" +
                "boardDepartments=" + boardDepartments +
                ", boardTasks=" + boardTasks +
                '}';
    }
}
