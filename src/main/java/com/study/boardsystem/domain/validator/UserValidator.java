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

        boolean isExistsNickName = userRepository.existsByNickName(userSaveRequestDto.getNickName());

        if (isExistsNickName) {
            errors.rejectValue("nickName", "wrong.value", "입력하신 닉네임을 사용할 수 없습니다.");
        }
    }
}
