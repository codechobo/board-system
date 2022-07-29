package com.study.boardsystem.web.dto.post_dto;

import com.study.boardsystem.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : PostSaveResponseDtd
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@Getter
@Builder
@AllArgsConstructor
public class PostSaveResponseDto {

    private final String nickname;
    private final String title;
    private final String description;

    public static PostSaveResponseDto toMapper(Post post, String nickname) {
        return PostSaveResponseDto.builder()
                .nickname(nickname)
                .title(post.getTitle())
                .description(post.getDescription())
                .build();
    }
}
