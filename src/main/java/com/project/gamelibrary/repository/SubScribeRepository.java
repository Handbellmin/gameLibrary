package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.SubScribe;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubScribeRepository {
    private final EntityManager em;

    public void save(SubScribe subScribe) {
        em.persist(subScribe);
    }


}
