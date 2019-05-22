package com.block.blocker.repositories;

import com.block.blocker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserReposiroty extends CrudRepository<User, Long> {
    User findByUsername(String name);
    Optional<User> findByEmail(String email);

}
