package com.study.boardsystem.exception;

import com.study.boardsystem.exception.code.CommonErrorCode;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.exception.code
 * fileName       : NotFoundEntityException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
public class NotFoundEntityException extends RuntimeException {

    private final CommonErrorCode errorCode;

    public NotFoundEntityException() {
        this.errorCode = CommonErrorCode.NOT_FOUND_ENTITY;
    }

    public NotFoundEntityException(CommonErrorCode errorCode) {
        super(CommonErrorCode.NOT_FOUND_ENTITY.getMessage());
        this.errorCode = CommonErrorCode.NOT_FOUND_ENTITY;
    }

}
