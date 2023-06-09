package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {
    List<Files> findByBoardId(Long boardId);
    Optional<Files> findById(Long fileId);
}
