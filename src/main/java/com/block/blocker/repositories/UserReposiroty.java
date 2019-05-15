package com.block.blocker.repositories;

import com.block.blocker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiroty extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User findById(Integer id);

}
