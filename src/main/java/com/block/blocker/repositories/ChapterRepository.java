package com.block.blocker.repositories;

import com.block.blocker.models.Chapter;
import com.block.blocker.models.Composition;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository <Chapter, Long> {

    Iterable<Chapter> findAllByCompositionOrderByIdDesc(Composition composition);

}
