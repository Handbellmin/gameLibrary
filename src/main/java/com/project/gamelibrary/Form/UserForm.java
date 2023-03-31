package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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
    @Builder
    public UserForm(String username, String password, String nickname, String userRole) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.userRole = userRole;
    }
}
