package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(BindingResult bindingResult,@Valid UserForm userForm) {
        if(bindingResult.hasErrors()){
            return "/joinForm";
        }
        userService.register(userForm);
        return "";
    }
}
