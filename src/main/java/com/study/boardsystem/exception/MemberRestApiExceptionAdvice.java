package com.study.boardsystem.exception;

import com.study.boardsystem.web.MemberController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : MemberRestApiControllerAdvice
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Log4j2
@RestControllerAdvice(basePackageClasses = MemberController.class)
public class MemberRestApiExceptionAdvice {

    @ExceptionHandler(value = NotFoundEntityException.class)
    public ResponseEntity<String> illegalArgumentException(NotFoundEntityException e) {
        log.info("Error Name : {}", e.getClass().getSimpleName());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
