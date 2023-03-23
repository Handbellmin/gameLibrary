package com.project.gamelibrary.repository.custom;

import com.project.gamelibrary.domain.BoardComment;

import java.util.List;

public interface BoardCommentCustomRepository {
    List<BoardComment> findAllByBoardId(Long BoardId);
}

