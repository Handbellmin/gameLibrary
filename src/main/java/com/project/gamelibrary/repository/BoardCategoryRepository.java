package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardCategory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCategoryRepository {

    private final EntityManager em;

    public void save(BoardCategory boardCategory) {
        em.persist(boardCategory);
    }
    public BoardCategory findOne(Long boardCategoryId) {
        return em.find(BoardCategory.class, boardCategoryId);
    }

    public List<BoardCategory> findAll() {
        return em.createQuery("select bc from BoardCategory bc", BoardCategory.class)
                .getResultList();
    }
}
