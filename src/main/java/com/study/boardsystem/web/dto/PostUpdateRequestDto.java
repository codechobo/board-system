package com.study.boardsystem.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostUpdateRequestDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequestDto {

    @NotNull
    @Length(max = 50)
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

}
