package com.study.boardsystem.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : ErrorCode
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_ENTITY(HttpStatus.NOT_FOUND, "Not Found Entity !!"),
    DUPLICATION_FIELD_VALUE(HttpStatus.BAD_REQUEST, "Duplication Field Value!!");

    private final HttpStatus httpStatus;
    private final String message;

}
