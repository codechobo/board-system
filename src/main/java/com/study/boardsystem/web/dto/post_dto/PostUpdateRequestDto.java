package com.study.boardsystem.web.dto.post_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * packageName    : com.study.boardsystem.web.dto.post
 * fileName       : PostUpdateRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/29
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequestDto {

    @NotBlank
    @Size(max = 150)
    @JsonProperty("title")
    private String title;

    @NotEmpty
    @Size
    @JsonProperty("description")
    private String description;

}
