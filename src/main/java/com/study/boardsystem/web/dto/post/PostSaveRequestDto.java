package com.study.boardsystem.web.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.boardsystem.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * packageName    : com.study.boardsystem.web.dto.post
 * fileName       : PostSaveReuqestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequestDto {

    @NotEmpty
    @Size(min = 1, max = 20)
    @JsonProperty("nickname")
    private String nickname;

    @NotBlank
    @Size(max = 150)
    @JsonProperty("title")
    private String title;

    @NotEmpty
    @Size
    @JsonProperty("description")
    private String description;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .description(this.description)
                .build();
    }

}
