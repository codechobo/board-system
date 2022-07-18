package com.study.boardsystem.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.boardsystem.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * packageName    : com.study.boardsystem.web.dto
 * fileName       : PostSaveRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequestDto {

    @NotNull
    @Length(max = 20)
    @JsonProperty("user_name")
    private String userName;

    @NotNull
    @Length(max = 50)
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

    public Post toEntity() {
        return Post.builder()
                .userName(this.userName)
                .title(this.title)
                .description(this.description)
                .build();
    }
}