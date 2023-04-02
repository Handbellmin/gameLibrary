package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    public User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
    public boolean existsByUsername(String username);

}
