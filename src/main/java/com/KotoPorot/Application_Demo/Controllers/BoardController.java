package com.KotoPorot.Application_Demo.Controllers;

import com.KotoPorot.Application_Demo.Entities.Department;
import com.KotoPorot.Application_Demo.Entities.Task;
import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.KotoPorot.Application_Demo.ResponseDTO.UserDTO;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateDepDTO;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateTaskDTO;
import com.KotoPorot.Application_Demo.RequestsDTO.MemberDTO;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateBoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.BoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.OwnerBoardDTO;
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
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createBoard")
    public ResponseEntity<UserRolesDTO> createBoard(@RequestBody CreateBoardDTO board, @NonNull Authentication authentication) {
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserPrincipal) {
            Users user = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            if (boardService.findBoardByName(board.getBoardName()) != null) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            return ResponseEntity.ok(boardService.createBoard(board.getBoardName(), user));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addBoardMember")
    public ResponseEntity<List<UserRolesDTO>> addSubscriber(@RequestBody MemberDTO request,
                                                            Authentication authentication) {

        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            Users executor = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
            Board board = boardService.findBoardByName(request.getBoardName());
            if (!boardService.isUserCanChangeBoard(executor, board)) {
                System.out.println("cant change");
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            }
            Users user = userService.findByUsername(request.getUserName());
            if (board == null || user == null || boardService.isUserMember(board, user)) {
                System.out.println(board);
                System.out.println(user);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            List<UserRolesDTO> members = boardService.addSubscriber(board, user);
            return ResponseEntity.ok(members);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/deleteBoardMember")
    public ResponseEntity<List<UserRolesDTO>> deleteMember(@RequestBody MemberDTO request,
                                                           Authentication authentication) {
        Users executor = userService.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername());
        Board board = boardService.findBoardByName(request.getBoardName());
        if (executor == null || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        Users user = userService.findByUsername(request.getUserName());
        if (board == null || user == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        try {
            return ResponseEntity.ok(boardService.deleteMember(user, board));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping("/{boardId}/createDepartment")
    public ResponseEntity<Department> createDepartment(@PathVariable Long boardId,
                                                       @RequestBody CreateDepDTO request, Authentication auth) {
        Users executor = userService.findByUsername(((UserPrincipal) auth.getPrincipal()).getUsername());
        Board board = boardService.findBoardById(boardId);
        if (board == null || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Department department = depService.createDepartment(request, board);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(department);

    }

    //Заменить MemberDTO на PathVariable user_id
    @PostMapping("/{boardId}/{departmentId}/addMember")
    public ResponseEntity<List<Users>> addDepMember(@PathVariable Long boardId,
                                                    @PathVariable Long departmentId,
                                                    @RequestBody MemberDTO memberDTO, Authentication auth) {

        Users executor = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        System.out.println(executor);
        Department department = depService.findDepartmentById(departmentId);
        System.out.println(department);
        Board board = boardService.findBoardById(boardId);
        System.out.println(board);
        Users member = userService.findByUsername(memberDTO.getUserName());
        System.out.println(member);

        if (executor == null || department == null || board == null || member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!boardService.isUserMember(board, member) || !boardService.isUserCanChangeBoard(executor, board)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (depService.isMember(member, department)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(depService.addMember(department, member));

    }


    //Дописать логику задач пользователя
    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDTO> getUserInfo(Authentication auth) {
        Users user = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/{departmentId}/{userId}/deleteDepMember")
    public ResponseEntity<List<Users>> deleteDepMember(@PathVariable Long departmentId,
                                                       @PathVariable Long userId, Authentication auth) {
        Users executor = userService.findById(((UserPrincipal) auth.getPrincipal()).getId());
        Department department = depService.findDepartmentById(departmentId);
        Users member = userService.findById(userId);

        if (executor == null || department == null || member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!boardService.isUserCanChangeBoard(executor, department.getBoard()) || !depService.isMember(member, department)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(depService.deleteMember(department, member));
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskDTO request, Authentication auth) {
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

    @GetMapping("/getBoardInfo")
    public ResponseEntity<BoardDTO> getBoardInfo(Authentication authentication) {
        Users user = userService.findById(((UserPrincipal) authentication.getPrincipal()).getId());
        Board board = boardService.findBoardById(user.getDefaultBoardId());
        BoardRole role = boardService.getUserBoardRole(user, board);
        if (role == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(boardService.getBoardInfo(board, role));
    }

}
