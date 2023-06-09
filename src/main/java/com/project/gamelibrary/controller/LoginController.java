package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.UserForm;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    // @Authentication이 활성화 되는 시기
    //
    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("userDetails: "+ userDetails.getUser());
        return "세션 정보 확인";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication,
                                               @AuthenticationPrincipal OAuth2User oauth){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication: " + oAuth2User.getAttributes());
        System.out.println("oauth2User :" + oauth.getAttributes());
        return "세션 정보 확인";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("userForm",new UserForm(null,null,null,null));
        return "joinForm";
    }

    @PostMapping("/join") //redirect를 붙이면 getMapping 옆 매핑 Url 실행
    public String join(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            return "joinForm";
        }
        if (userService.existsUsername(userForm.getUsername())){
            redirectAttributes.addFlashAttribute("error","중복된 아이디입니다.");
            return "redirect:/joinForm";
        }
        String rawPassword = userForm.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userForm.setPassword(encPassword);
        userService.register(userForm);
        return "redirect:/loginForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String info(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        Page<UserForm> userFormPage = userService.select(pageNumber,5);
        model.addAttribute("userList", userFormPage);
        return "users/userList";
    }

    @GetMapping("/user")
    public @ResponseBody String userinfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails : " + principalDetails.getUser());
        return "user";
    }


}
