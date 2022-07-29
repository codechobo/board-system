package com.study.boardsystem.web.dto.member_dto;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.type.Address;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : MeberResponseSaveDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Getter
public class MemberSaveResponseDto {

    private final String name;
    private final String nickname;
    private final String email;
    private final Address address;

    @Builder
    public MemberSaveResponseDto(Member member) {
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.address = member.getAddress();
    }
}
