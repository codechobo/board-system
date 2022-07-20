package com.study.boardsystem.module.user.web.dto;

import com.study.boardsystem.module.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : UserSaveRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */
@Data
@NoArgsConstructor
public class UserSaveRequestDto {

    @NotNull
    @Length(min = 2, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{2,20}$")
    private String name;

    @Email
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    @Length(min = 8, max = 20, message = "8자 ~ 20자 사이의 비밀번호")
    private String password;

    @NotBlank
    private String city;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

    @Builder
    public UserSaveRequestDto(String name, String email, String nickname, String password, String city, String address1, String address2) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
    }

    public User toEntity() {
        return  User.builder()
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .city(this.city)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }
}
