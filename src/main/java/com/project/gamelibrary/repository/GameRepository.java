package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Game;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameRepository {
    private final EntityManager em;

    public void save(Game game) {
        em.persist(this);
    }

    public Game findOne(Long id) {
        return em.find(Game.class, id);
    }
    public Game findByName(String gameName) {
        return em.find(Game.class, gameName);
    }

    public List<Game> findAll() {
        return em.createQuery("select g from Game g", Game.class)
                .getResultList();
    }
}
