package com.study.boardsystem.exception;

import com.study.boardsystem.exception.dto.ErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : MemberRestApiControllerAdvice
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Log4j2
@RestControllerAdvice
public class GlobalRestApiExceptionAdvice {

    @ExceptionHandler(value = {NotFoundEntityException.class, DuplicationException.class})
    public ResponseEntity<ErrorResponseDto> notFoundEntityException(HandlerException e) {

        ErrorResponseDto errorResponseDto = ErrorResponseDto.create(
                e.getErrorCode().getHttpStatus().value(),
                e.getErrorCode().getMessage(),
                LocalDateTime.now());

        log.info("Error Name : {}", e.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(bindingResult.getFieldError().getDefaultMessage())
                .fieldErrors(bindingResult.getFieldErrors())
                .build();

        log.info("Error Name : {}", e.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

}
