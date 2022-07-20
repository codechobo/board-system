package com.study.boardsystem.module.comment.dto;

import com.study.boardsystem.module.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.study.boardsystem.module.comment.dto
 * fileName       : CommentSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@Getter
@Builder
@RequiredArgsConstructor
public class CommentSaveResponseDto {

    private final String userNickname;
    private final String content;
    private final Long postId;

    public static CommentSaveResponseDto toMapper(Comment comment) {
        return CommentSaveResponseDto.builder()
                .userNickname(comment.getUserNickname())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .build();
    }

}
