package com.KotoPorot.Application_Demo.ResponseDTO;

import com.KotoPorot.Application_Demo.Entities.Department;
import com.KotoPorot.Application_Demo.Entities.Task;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Enums.BoardRole;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long userId;
    private String username;

    private List<UserBoards> userBoards;

    private List<UserDepartments> userDepartments;

    private List<Task> userTasks;
    private Department respDep;
    private Long defaultBoardId;

    public UserDTO() {
    }

    public UserDTO(Users user) {
       this.userId= user.getId();
       this.username= user.getUsername();
       this.respDep=user.getRespDep();
       this.defaultBoardId=user.getDefaultBoardId();

       this.userBoards = user.getRoles().stream().map(usersRoles -> new UserBoards(
               usersRoles.getBoard().getId(),
               usersRoles.getBoard().getName(),
               usersRoles.getBoardRole())).collect(Collectors.toList());

       this.userDepartments = user.getDepartments().stream().map(department -> new UserDepartments(
               department.getId(), department.getName(),
               department.getBoard().getId(), department.getBoard().getName()
       )).collect(Collectors.toList());
       this.userTasks = user.getUserTasks();
  }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserBoards> getUserBoards() {
        return userBoards;
    }

    public void setUserBoards(List<UserBoards> userBoards) {
        this.userBoards = userBoards;
    }

    public List<UserDepartments> getUserDepartments() {
        return userDepartments;
    }

    public void setUserDepartments(List<UserDepartments> userDepartments) {
        this.userDepartments = userDepartments;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public Department getRespDep() {
        return respDep;
    }

    public void setRespDep(Department respDep) {
        this.respDep = respDep;
    }

    public Long getDefaultBoardId() {
        return defaultBoardId;
    }

    public void setDefaultBoardId(Long defaultBoardId) {
        this.defaultBoardId = defaultBoardId;
    }


    public class UserBoards{
        private Long boardId;
        private String boardName;
        private BoardRole boardRole;

        public UserBoards(Long boardId, String boardName, BoardRole boardRole) {
            this.boardId = boardId;
            this.boardName = boardName;
            this.boardRole = boardRole;
        }

        public UserBoards() {
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

        public BoardRole getBoardRole() {
            return boardRole;
        }

        public void setBoardRole(BoardRole boardRole) {
            this.boardRole = boardRole;
        }
    }

    public class UserDepartments{
        private Long depId;
        private String depName;
        private String boardName;
        private Long boardId;

        public UserDepartments(Long depId, String depName, Long boardId, String boardName) {
            this.depId = depId;
            this.depName = depName;
            this.boardName = boardName;
            this.boardId = boardId;
        }

        public UserDepartments() {
        }

        public Long getDepId() {
            return depId;
        }

        public void setDepId(Long depId) {
            this.depId = depId;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getBoardName() {
            return boardName;
        }

        public void setBoardName(String boardName) {
            this.boardName = boardName;
        }

        public Long getBoardId() {
            return boardId;
        }

        public void setBoardId(Long boardId) {
            this.boardId = boardId;
        }
    }
}
