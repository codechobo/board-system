package com.study.boardsystem.web.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * packageName    : com.study.boardsystem.web
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

    @NotBlank
    private String city;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;
}
