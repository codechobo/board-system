package com.study.boardsystem.module.post.web.dto;

import com.study.boardsystem.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.module.post.web
 * fileName       : PostSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@Getter
public class PostSaveResponseDto {

    private final String userNickname;
    private final String title;

    @Builder
    public PostSaveResponseDto(Post post) {
        this.userNickname = post.getUserNickname();
        this.title = post.getTitle();
    }
}
