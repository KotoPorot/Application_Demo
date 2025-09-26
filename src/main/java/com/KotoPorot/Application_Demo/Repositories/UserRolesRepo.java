package com.KotoPorot.Application_Demo.Repositories;

import com.KotoPorot.Application_Demo.Entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<UsersRoles, Long> {

    boolean existsByUserIdAndBoardId(long id, Long id1);
}
