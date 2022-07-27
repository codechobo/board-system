package com.study.boardsystem.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.boardsystem.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : MemberUpdateRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberUpdateRequestDto {

    @NotEmpty
    @Size(min = 1, max = 20)
    @JsonProperty("nickname")
    private String nickname;

    @Email
    @NotEmpty
    @JsonProperty("email")
    private String email;

    @NotEmpty
    @Size(min = 5, max = 30)
    @JsonProperty("password")
    private String password;

    public Member toEntity() {
        return Member.builder()
                .nickname(this.nickname)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
