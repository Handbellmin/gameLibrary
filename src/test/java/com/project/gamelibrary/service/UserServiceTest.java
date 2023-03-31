package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void selectUserPaging() throws Exception {
        //given
        UserForm user1 = new UserForm("aa","aa","aa","ROLE_USER");
        userService.register(user1);

        UserForm user2 = new UserForm("b","b","b","ROLE_USER");
        userService.register(user2);

        UserForm user3 = new UserForm("c","c","c","ROLE_USER");
        userService.register(user3);

        UserForm user4 = new UserForm("d","d","d","ROLE_USER");
        userService.register(user4);
        //when
        userService.printUsers();
        //then
    }

}