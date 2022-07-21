package com.study.boardsystem.module.comment.web.dto;

import com.study.boardsystem.module.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.study.boardsystem.module.comment.web.dto
 * fileName       : CommentSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@Getter
@Builder
@RequiredArgsConstructor
public class CommentSaveResponseDto {

    private final String content;
    private final String userNickname;

    public static CommentSaveResponseDto toMapper(Comment comment) {
        return new CommentSaveResponseDto(
                comment.getContent(),
                comment.getUserNickname()
        );
    }
}
