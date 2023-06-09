package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;



public interface BoardRepository extends CrudRepository<Board,Long> {
    Page<Board> findAll(Pageable pageable);
}
