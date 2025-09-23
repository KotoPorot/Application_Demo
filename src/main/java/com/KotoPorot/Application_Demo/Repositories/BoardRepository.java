package com.KotoPorot.Application_Demo.Repositories;

import com.KotoPorot.Application_Demo.Entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findBoardByName(String name);

    Board findBoardById(Long id);
}
