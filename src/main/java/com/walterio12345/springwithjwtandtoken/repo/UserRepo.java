package com.walterio12345.springwithjwtandtoken.repo;

import com.walterio12345.springwithjwtandtoken.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
