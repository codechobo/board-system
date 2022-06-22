package com.study.boardsystem.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/users")
    public ResponseEntity<Long> createUser(@RequestBody UserSaveRequestDto requestDto) {
        Long joinId = userService.join(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(joinId);
    }

}
