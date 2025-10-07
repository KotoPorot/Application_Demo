package com.KotoPorot.Application_Demo.Controllers;

import com.KotoPorot.Application_Demo.Entities.Department;
import com.KotoPorot.Application_Demo.Entities.Task;
import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.KotoPorot.Application_Demo.RequestsDTO.*;
import com.KotoPorot.Application_Demo.ResponseDTO.UserDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.BoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.UserRolesDTO;
import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Login_registration.DTO.UserPrincipal;
import com.KotoPorot.Application_Demo.Login_registration.Service.UserService;
import com.KotoPorot.Application_Demo.Services.BoardService;
import com.KotoPorot.Application_Demo.Services.DepartmentService;
import com.KotoPorot.Application_Demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/")
public class BoardController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private DepartmentService depService;
    @Autowired
    private TaskService taskService;

    //All OK
    @PostMapping("/createBoard")
    public ResponseEntity<UserRolesDTO> createBoard(@RequestBody CreateBoardRequestDTO board,
                                                    Authentication authentication) {
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserPrincipal) {
            Users user = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            if (boardService.findBoardByName(board.getBoardName()) != null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            return ResponseEntity.ok(boardService.createBoard(board.getBoardName(), user));
        }
        return ResponseEntity.badRequest().build();
    }

    //All OK
    @PostMapping("/addBoardMember")
    public ResponseEntity<String> addSubscriber(@RequestBody AddBoardMemberRequestDTO request,
                                                Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            Users executor = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            Board board = boardService.findBoardById(request.getBoardId());
            if (!boardService.isUserCanChangeBoard(executor, board)) {
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            }
            Users user = userService.findByUsername(request.getUserName());
            if (board == null || user == null || boardService.isUserMember(board, user)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            try {
                boardService.addBoardMember(board, user, request.getBoardRole());
                return ResponseEntity.ok().build();
            } catch (SecurityException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


    //All OK
    @PostMapping("/deleteBoardMember")
    public ResponseEntity<String> deleteMember(@RequestBody DeleteBoardMemberRequestDTO request,
                                               Authentication authentication) {
        Users executor = userService.findById(((UserPrincipal) authentication.getPrincipal()).getId());
        Board board = boardService.findBoardById(request.getBoardId());
        if (executor == null || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        Users user = userService.findById(request.getUserId());
        if (board == null || user == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        try {
            boardService.deleteMember(user, board);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    //All OK
    @PostMapping("/createDepartment")
    public ResponseEntity<Department> createDepartment(@RequestBody CreateDepRequestDTO request,
                                                       Authentication auth) {
        Users executor = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        Board board = boardService.findBoardById(request.getBoardId());
        if (board == null || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Department department = depService.createDepartment(request, board);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(department);
    }


    //All OK
    @PostMapping("/addDepartmentMember")
    public ResponseEntity<List<Users>> addDepMember(@RequestBody AddDepMemberRequestDTO request,
                                                    Authentication auth) {

        Users executor = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        Department department = depService.findDepartmentById(request.getDepartmentId());
        Board board = boardService.findBoardById(request.getBoardId());
        Users member = userService.findById(request.getUserId());

        if (executor == null || department == null || board == null || member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!boardService.isUserMember(board, member) || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (depService.isMember(member, department)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        depService.addMember(department, member);
        return ResponseEntity.ok().build();

    }


    //All OK
    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDTO> getUserInfo(Authentication auth) {
        Users user = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        return ResponseEntity.ok(new UserDTO(user));
    }

    //All OK
    @PostMapping("/deleteDepMember")
    public ResponseEntity<String> deleteDepMember(@RequestBody DeleteDepMemberDTO request,
                                                  Authentication auth) {
        Users executor = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        Department department = depService.findDepartmentById(request.getDepartmentId());
        Users member = userService.findById(request.getUserId());

        if (executor == null || department == null || member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!boardService.isUserCanChangeBoard(executor, department.getBoard()) || !depService.isMember(member, department)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        depService.deleteMember(department, member);
        return ResponseEntity.ok().build();
    }

    //All OK
    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequestDTO request, Authentication auth) {
        Board board = boardService.findBoardById(request.getBoardId());

        if (board == null || request.getTitle() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!boardService.isUserMember(board,
                userService.findById(((UserPrincipal) auth.getPrincipal()).getId()))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        try {
            Task validTask = boardService.validateTaskRequest(request);
            return ResponseEntity.ok(taskService.saveTask(validTask));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    //Change getting board info by roles (now all as owner)
    @GetMapping("/getBoardInfo")
    public ResponseEntity<BoardDTO> getBoardInfo(Authentication authentication) {
        Users user = userService.findById(((UserPrincipal) authentication.getPrincipal()).getId());
        Board board = boardService.findBoardById(user.getDefaultBoardId());
        if (user == null || board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BoardRole role = boardService.getUserBoardRole(user, board);
        if (role == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(boardService.getBoardInfo(board, role));
    }


    @PostMapping("/setdefaultboard")
    public ResponseEntity<String> setDefBoard(@RequestBody DefBoardRequestDTO defBoardDTO, Authentication auth) {
        Users user = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        Board board = boardService.findBoardById(defBoardDTO.getDefaultBoardId());
        if (user == null || board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!boardService.isUserMember(board, user)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        try {
            user = userService.setDefaultBoard(user, board);
            if(user.getDefaultBoardId().equals(defBoardDTO.getDefaultBoardId())){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else{
                throw new Error("something went wrong");
            }

        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
