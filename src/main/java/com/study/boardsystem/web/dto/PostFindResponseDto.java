package com.study.boardsystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : PostDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFindResponseDto {

    private String title;
    private String description;

}
