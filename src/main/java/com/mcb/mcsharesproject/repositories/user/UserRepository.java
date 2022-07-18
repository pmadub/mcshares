package com.mcb.mcsharesproject.repositories.user;

import com.mcb.mcsharesproject.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

   User findByUserName(String userName);
}
