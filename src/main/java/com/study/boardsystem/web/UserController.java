package com.study.boardsystem.web;

import com.study.boardsystem.web.dto.UserSaveRequestDto;
import com.study.boardsystem.service.UserService;
import com.study.boardsystem.domain.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserController
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @InitBinder("userSaveRequestDto")
    public void UserValidatorInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidator);
    }

    @PostMapping("/users")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto) {
        Long joinId = userService.join(userSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(joinId);
    }

}
