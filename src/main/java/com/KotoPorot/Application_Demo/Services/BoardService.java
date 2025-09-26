package com.KotoPorot.Application_Demo.Services;

import com.KotoPorot.Application_Demo.ResponseDTO.UserRolesDTO;
import com.KotoPorot.Application_Demo.Entities.Board;
import com.KotoPorot.Application_Demo.Entities.Users;
import com.KotoPorot.Application_Demo.Entities.UsersRoles;
import com.KotoPorot.Application_Demo.Enums.BoardRole;
import com.KotoPorot.Application_Demo.Repositories.BoardRepository;
import com.KotoPorot.Application_Demo.Repositories.UserRolesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRolesRepo userRolesRepo;


    public UserRolesDTO createBoard(String name, Users user) {
        Board board = new Board();
        board.setOwner(user.getUsername());
        board.setName(name);
        board.getMembers().add(new UsersRoles(user, board, BoardRole.OWNER));
        return new UserRolesDTO(boardRepository.save(board).getMembers().getFirst());
    }

    public Board findBoardByName(String boardName) {
        return boardRepository.findBoardByName(boardName);
    }

    @Transactional
    public List<UserRolesDTO> addSubscriber(Board board, Users user) {
            board.setUsersRoles(user, BoardRole.MEMBER);
            boardRepository.save(board);

            return board.getMembers().stream()
                    .map(UserRolesDTO::new).collect(Collectors.toList());
        }

    public boolean isUserMember(Board board, Users user) {
      return userRolesRepo.existsByUserIdAndBoardId(user.getId(), board.getId());
    }




    public boolean isUserCanChangeBoard(Users user, Board board){
        List<UsersRoles> boardMembers = board.getMembers();

        UsersRoles usersRoles = boardMembers.stream().filter(ur -> ur.getUser().equals(user))
                .findAny()
                .orElse(null);

        if(usersRoles!=null&&(usersRoles.getBoardRole().equals(BoardRole.OWNER)
                ||usersRoles.getBoardRole().equals(BoardRole.MANAGER))){
            return true;
        }else {
            return false;
        }
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
