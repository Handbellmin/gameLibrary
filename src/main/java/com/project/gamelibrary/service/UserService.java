package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.domain.User;
import com.project.gamelibrary.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserForm userForm) {
        User user = new User.Builder(userForm.getUsername(), userForm.getPassword())
                .setUserNickName(userForm.getNickname())
                .setCreatDate(LocalDateTime.now())
                .setUserRole("ROLE_USER")
                .build();
        userRepository.save(user);
    }
}
