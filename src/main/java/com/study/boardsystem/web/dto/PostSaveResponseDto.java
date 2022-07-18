package com.study.boardsystem.web.dto;

import com.study.boardsystem.domain.Post;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@Getter
public class PostSaveResponseDto {

    private final String userName;
    private final String title;

    @Builder
    public PostSaveResponseDto(Post post) {
        this.userName = post.getUserName();
        this.title = post.getTitle();
    }
}
