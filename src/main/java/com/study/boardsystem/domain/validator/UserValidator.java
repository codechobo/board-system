package com.study.boardsystem.domain.validator;

import com.study.boardsystem.domain.UserRepository;
import com.study.boardsystem.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserValidator
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserSaveRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSaveRequestDto userSaveRequestDto = (UserSaveRequestDto) target;

        boolean isExistsNickName = userRepository.existsByNickname(userSaveRequestDto.getNickname());
        boolean isExistEmail = userRepository.existsByEmail(userSaveRequestDto.getEmail());

        if (isExistsNickName) {
            errors.rejectValue("nickname", "wrong.nickname", "입력하신 닉네임을 사용할 수 없습니다.");
        }

        if (isExistEmail) {
            errors.rejectValue("email", "wrong.email", "입력하신 이메일을 사용할 수 없습니다.");
        }

    }
}
