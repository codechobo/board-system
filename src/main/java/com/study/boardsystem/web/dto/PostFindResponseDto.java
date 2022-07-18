package com.study.boardsystem.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : PostDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@Getter
@RequiredArgsConstructor
public class PostFindResponseDto {

    private final String title;
    private final String description;

}
