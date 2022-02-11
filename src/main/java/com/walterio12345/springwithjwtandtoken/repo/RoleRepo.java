package com.walterio12345.springwithjwtandtoken.repo;


import com.walterio12345.springwithjwtandtoken.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
