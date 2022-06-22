package com.study.boardsystem.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserSaveRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */
@Data
public class UserSaveRequestDto {

    @NotNull
    @Length(min = 2, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{2,20}$")
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    @Length(min = 8, max = 20, message = "8자 ~ 20자 사이의 비밀번호")
    private String password;

    @NotBlank
    private String city;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

}
