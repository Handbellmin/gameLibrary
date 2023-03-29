package com.project.gamelibrary.repository;

import com.project.gamelibrary.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {
}
