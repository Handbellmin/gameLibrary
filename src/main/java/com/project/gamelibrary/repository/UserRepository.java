package com.project.gamelibrary.repository;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

}
