package com.study.boardsystem.exception;

import com.study.boardsystem.exception.code.ErrorCode;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : DuplicationException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

public class DuplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DuplicationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
