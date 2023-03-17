package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void save(Board board) {em.persist(board);}

    public Board findOne(Long boardId) {
        return em.find(Board.class, boardId);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }


}
