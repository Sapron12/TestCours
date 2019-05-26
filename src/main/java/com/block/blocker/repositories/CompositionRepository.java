package com.block.blocker.repositories;

import com.block.blocker.models.Composition;
import com.block.blocker.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CompositionRepository extends CrudRepository<Composition, Long> {

    Iterable<Composition> findAllByAuthorOrderByIdDesc(User author);
}
