package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long> {

}
