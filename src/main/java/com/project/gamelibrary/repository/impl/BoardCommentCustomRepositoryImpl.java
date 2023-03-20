package com.project.gamelibrary.repository.impl;

import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.repository.custom.BoardCommentCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCommentCustomRepositoryImpl implements BoardCommentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardComment> findAllByBoardId() {
        return null;
    }
}
