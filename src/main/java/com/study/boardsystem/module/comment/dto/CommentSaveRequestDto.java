package com.study.boardsystem.module.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.boardsystem.module.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * packageName    : com.study.boardsystem.module.comment.dto
 * fileName       : CommentSaveRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequestDto {

    @NotNull
    @Length(max = 200)
    @JsonProperty("content")
    private String content;

    @NotNull
    @JsonProperty("user_nickname")
    private String userNickname;

    public Comment toEntity() {
        return Comment.builder()
                .content(this.content)
                .userNickname(this.userNickname)
                .build();
    }

}
