package com.study.boardsystem.module.user.web;

import com.study.boardsystem.module.user.web.dto.UserSaveRequestDto;
import com.study.boardsystem.module.user.service.UserService;
import com.study.boardsystem.module.user.domain.validator.UserValidator;
import com.study.boardsystem.module.user.web.dto.UserSaveResponseDto;
import com.study.boardsystem.module.user.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : UserController
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@RestController
@RequestMapping("/api")
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

    @GetMapping("/users/{id}")
    public ResponseEntity<UserSaveResponseDto> getUser(@PathVariable("id") Long userId) {
        UserSaveResponseDto userSaveResponseDto = userService.findUser(userId);
        return ResponseEntity.ok().body(userSaveResponseDto);
    }

    @GetMapping("/users")
    public ResponseEntity<UserSaveResponseDto> getUser(@RequestParam String nicknameOrEmail) {
        UserSaveResponseDto userSaveResponseDto = userService.findUserEmail(nicknameOrEmail);
        return ResponseEntity.ok().body(userSaveResponseDto);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Long> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        Long id = userService.updateUserInfo(userId, userUpdateRequestDto);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        Long id = userService.deleteUserInfo(userId);
        return ResponseEntity.ok().body(id);
    }

}
