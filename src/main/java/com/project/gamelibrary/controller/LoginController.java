package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("userDetails: "+ userDetails.getUser());
        return "세션 정보 확인";
    }
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "joinForm";
    }

    @PostMapping("/join") //redirect를 붙이면 getMapping 옆 매핑 Url 실행
    public String join(@Valid @ModelAttribute UserForm userForm,BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "joinForm";
        }
        String rawPassword = userForm.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userForm.setPassword(encPassword);
        userService.register(userForm);
        return "redirect:/loginForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }
}
