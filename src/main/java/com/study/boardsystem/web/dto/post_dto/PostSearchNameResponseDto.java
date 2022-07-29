package com.study.boardsystem.web.dto.post_dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostSearchNameResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/29
 */

@Getter
public class PostSearchNameResponseDto {

    private final String nickname;
    private final String title;
    private final LocalDateTime createDateTime;

    @Builder
    public PostSearchNameResponseDto(String nickname, String title, LocalDateTime createDateTime) {
        this.nickname = nickname;
        this.title = title;
        this.createDateTime = createDateTime;
    }
}
