package com.study.boardsystem.web.dto.member_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.type.Address;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : MemberSaveRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveRequestDto {

    @NotEmpty
    @Size(min = 1, max = 100)
    @JsonProperty("name")
    private String name;

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

    @NotNull
    @JsonProperty("address")
    private Address address;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .nickname(this.nickname)
                .email(this.email)
                .password(this.password)
                .address(this.address)
                .build();
    }

}
