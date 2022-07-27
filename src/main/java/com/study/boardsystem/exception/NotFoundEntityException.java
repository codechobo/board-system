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
public class NotFoundEntityException extends HandlerException {

    public NotFoundEntityException(CommonErrorCode errorCode) {
        super(errorCode.getMessage(), CommonErrorCode.NOT_FOUND_ENTITY);
    }
}
