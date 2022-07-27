package com.study.boardsystem.exception;

import com.study.boardsystem.exception.code.ErrorCode;

/**
 * packageName    : com.study.boardsystem.exception.code
 * fileName       : NotFoundEntityException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */
public class NotFoundEntityException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotFoundEntityException() {
        this.errorCode = ErrorCode.NOT_FOUND_ENTITY;
    }

    public NotFoundEntityException(String message, ErrorCode errorCode) {
        super(ErrorCode.NOT_FOUND_ENTITY.getMessage());
        this.errorCode = ErrorCode.NOT_FOUND_ENTITY;
    }

}
