package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.domain.User;
import com.project.gamelibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Page<UserForm> select(int pageNumber, int pageSize){
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,("username")));
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(m ->new UserForm(m.getUsername(), m.getPassword(), m.getUserNickName(), m.getUserRole()));
    }
    public void printUsers() {
        int pageNumber = 0;
        int pageSize = 3;
        Page<UserForm> userPages = select(pageNumber, pageSize);
        while(true) {
            List<UserForm> users = userPages.getContent();
            for(UserForm user : users) {
                System.out.println(user.getUsername());
                System.out.println(pageNumber);
            }
            if(!userPages.hasNext()){
                break;
            }
            pageNumber++;
            userPages = select(pageNumber,pageSize);
        }
    }
    public void register(UserForm userForm) {
        User user = new User.Builder(userForm.getUsername(), userForm.getPassword())
                .setUserNickName(userForm.getNickname())
                .setCrateDate(LocalDateTime.now())
                .setUserRole("ROLE_USER")
                .build();
        if(existsUsername(userForm.getUsername())){
            log.error("중복된 아이디 회원가입 요청입니다.");
            return;
        }
        userRepository.save(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
