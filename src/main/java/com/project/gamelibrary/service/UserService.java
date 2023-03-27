package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.domain.User;
import com.project.gamelibrary.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserForm userForm) {
        User user = new User.Builder(userForm.getUsername(), userForm.getPassword())
                .setUserNickName(userForm.getNickname())
                .setCrateDate(LocalDateTime.now())
                .setUserRole("ROLE_USER")
                .build();
        userRepository.save(user);
    }
}
