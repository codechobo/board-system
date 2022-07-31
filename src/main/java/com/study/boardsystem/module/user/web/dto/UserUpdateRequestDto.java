package com.study.boardsystem.module.user.web.dto;

import com.study.boardsystem.module.user.domain.type.Address;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.study.boardsystem.module.user.web
 * fileName       : UserUpdateRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/23
 */

@Data
public class UserUpdateRequestDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private Address address;
}
