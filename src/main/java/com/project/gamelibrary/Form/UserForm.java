package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {
    @NotEmpty(message = "아이디를 입력해주세요.")
    private String username;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max=10, message = "길이가 맞지 않습니다.")

    private String password;
    private String nickname;
    private String userRole;

    private String street;
    private String street_detail;
}
