package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.Entities.*;
import com.KotoPorot.Application_Demo.Enums.TaskStatus;
import com.KotoPorot.Application_Demo.Repositories.DepRepository;
import com.KotoPorot.Application_Demo.Repositories.UserRepository;
import com.KotoPorot.Application_Demo.RequestsDTO.CreateTaskRequestDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.BoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.OwnerBoardDTO;
import com.KotoPorot.Application_Demo.ResponseDTO.UserRolesDTO;
import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.KotoPorot.Application_Demo.Repositories.BoardRepository;
import com.KotoPorot.Application_Demo.Repositories.UserRolesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRolesRepo userRolesRepo;
    @Autowired
    private DepRepository depRepository;
    @Autowired
    private UserRepository userRepository;


    public UserRolesDTO createBoard(String name, Users user) {
        Board board = new Board();
        board.setOwner(user.getUsername());
        board.setName(name);
        board.getMembers().add(new UsersRoles(user, board, BoardRole.OWNER));
        board = boardRepository.save(board);
        user.setDefaultBoardId(board.getId());
        userRepository.save(user);
        return new UserRolesDTO(board.getMembers().getFirst());
    }

    public Board findBoardByName(String boardName) {
        return boardRepository.findBoardByName(boardName);
    }

    @Transactional
    public List<UserRolesDTO> addBoardMember(Board board, Users user, BoardRole role) {
        if (role != null) {
            if (!role.equals(BoardRole.OWNER)) {
                board.setUsersRoles(user, role);
            } else {
                throw new SecurityException();
            }
        } else {
            board.setUsersRoles(user, BoardRole.MEMBER);
        }
        board = boardRepository.save(board);
        if (user.getDefaultBoardId() == null) {
            user.setDefaultBoardId(board.getId());
            userRepository.save(user);
        }
        return board.getMembers().stream()
                .map(UserRolesDTO::new).collect(Collectors.toList());
    }

    public boolean isUserMember(Board board, Users user) {
        if (user == null || board == null) {
            return false;
        }
        return userRolesRepo.existsByUserIdAndBoardId(user.getId(), board.getId());
    }


    public boolean isUserCanChangeBoard(Users user, Board board) {
        UsersRoles usersRoles = board.getMembers().stream().filter(ur -> ur.getUser().equals(user))
                .findAny()
                .orElse(null);

        if (usersRoles != null && (usersRoles.getBoardRole().equals(BoardRole.OWNER)
                || usersRoles.getBoardRole().equals(BoardRole.MANAGER))) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<UserRolesDTO> deleteMember(Users user, Board board) {
        UsersRoles userRole = board.getMembers().stream()
                .filter(ur -> ur.getUser().getId().equals(user.getId()))
                .findFirst().orElse(null);

        if (userRole == null || userRole.getBoardRole().equals(BoardRole.OWNER)) {
            throw new IllegalArgumentException();
        } else {
            board.getMembers().remove(userRole);
            user.getRoles().remove(userRole);
            boardRepository.save(board);
            if (user.getDefaultBoardId().equals(board.getId())) {
                if (!user.getRoles().isEmpty()) {
                    user.setDefaultBoardId(user.getRoles().getFirst().getBoard().getId());
                } else {
                    user.setDefaultBoardId(null);
                }
            }

        }
        return board.getMembers().stream().map(UserRolesDTO::new).collect(Collectors.toList());


    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public Task validateTaskRequest(CreateTaskRequestDTO request) {
        Task task = new Task();
        Board board = findBoardById(request.getBoardId());
        task.setName(request.getTitle());
        task.setBoard(board);
        task.setTaskStatus(TaskStatus.NOTSTARTED);
        task.setCreatedDate(LocalDateTime.now());

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getDepartmentId() != null) {
            Department department = depRepository.findById(request.getDepartmentId()).orElse(null);
            if (department != null && board.getDepartments().contains(department)) {
                task.setDepartment(department);
            } else {
                throw new IllegalArgumentException("Wrong department!");
            }
        }

        if (request.getExecutorId() != null) {
            Users executor = userRepository.findById(request.getExecutorId()).orElse(null);
            if (executor != null && isUserMember(board, executor)) {
                task.setExecutor(executor);
            }
        }
        return task;
    }

    public BoardRole getUserBoardRole(Users user, Board board) {
        UsersRoles userRole = board.getMembers().stream()
                .filter(ur -> ur.getUser().getId().equals(user.getId()))
                .findFirst().orElse(null);

        if (userRole != null) {
            return userRole.getBoardRole();
        } else return null;

    }

    public BoardDTO getBoardInfo(Board board, BoardRole role) {
        if (role == BoardRole.OWNER || role == BoardRole.MANAGER || role == BoardRole.MEMBER) {
            return new OwnerBoardDTO(board);
        } else return null;
    }

//    public List<SubscriberDTO> getBoardSubscribers(Board board){
//        List<Users> users = board.getMembers();
//        List<SubscriberDTO> subscriberDTOS = new ArrayList<>();
//        for (Users user:users){
//            subscriberDTOS.add(new SubscriberDTO(user.getUsername(), user.getId(),
//                    board.getName(), board.getId()));
//        }
//        return subscriberDTOS;
//    }
//
//
//    public Board findBoardById(Long id) {
//        return boardRepository.findBoardById(id);
//    }
}
