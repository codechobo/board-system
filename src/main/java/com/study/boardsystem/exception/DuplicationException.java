package com.study.boardsystem.exception;

import com.study.boardsystem.exception.code.CommonErrorCode;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : DuplicationException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
public class DuplicationException extends HandlerException{

    public DuplicationException(CommonErrorCode errorCode) {
        super(errorCode.getMessage(), CommonErrorCode.DUPLICATION_FIELD_VALUE);
    }

}
