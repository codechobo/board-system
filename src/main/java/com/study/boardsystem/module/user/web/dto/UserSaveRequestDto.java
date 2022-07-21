package com.study.boardsystem.module.user.web.dto;

import com.study.boardsystem.module.user.domain.type.Address;
import com.study.boardsystem.module.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
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

    @NotNull
    private Address address;

    @Builder
    public UserSaveRequestDto(String name, String email, String nickname, String password, Address address) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
    }

    public User toEntity() {
        return  User.builder()
                .name(this.name)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .address(this.address)
                .build();
    }
}
