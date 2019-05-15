package com.block.blocker.repositories;

import com.block.blocker.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ShowedUsersRepository extends CrudRepository<User, Long> {
}
