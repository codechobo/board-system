package com.study.boardsystem.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

/**
 * packageName    : com.study.boardsystem.exception.dto
 * fileName       : ErrorResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
@JsonInclude(Include.NON_NULL)
public class ErrorResponseDto {

    private final int statusCode;
    private final String message;
    private List<FieldError> fieldErrors;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Builder
    public ErrorResponseDto(int statusCode, String message, List<FieldError> fieldErrors) {
        this.statusCode = statusCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public static ErrorResponseDto create(int statusCode, String message, LocalDateTime timestamp) {
        return ErrorResponseDto.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
