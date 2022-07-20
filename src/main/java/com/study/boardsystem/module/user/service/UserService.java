package com.study.boardsystem.module.user.service;

import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import com.study.boardsystem.module.user.web.dto.UserSaveRequestDto;
import com.study.boardsystem.module.user.web.dto.UserSaveResponseDto;
import com.study.boardsystem.module.user.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : UserService
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long join(UserSaveRequestDto userSaveRequestDto) {
        if (userRepository.existsByNickname(userSaveRequestDto.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 정보입니다.");
        }

        if (userRepository.existsByEmail(userSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일 정보입니다.");
        }

        User user = userSaveRequestDto.toEntity();
        user.joinCheck(true);

        userRepository.save(user);
        return user.getId();
    }

    public UserSaveResponseDto findUser(Long id) {
        User user = findUsersById(id);
        return modelMapper.map(user, UserSaveResponseDto.class);
    }

    @Transactional
    public Long updateUserInfo(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = findUsersById(userId);
        user.updateEmail(userUpdateRequestDto.getEmail());
        user.updateNickname(userUpdateRequestDto.getNickname());
        user.updateAddress(userUpdateRequestDto.getCity(), userUpdateRequestDto.getAddress1(), userUpdateRequestDto.getAddress2());
        return user.getId();
    }

    private User findUsersById(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 회원입니다."));
    }

    public UserSaveResponseDto findUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        user.emailCheck(email);

        return modelMapper.map(user, UserSaveResponseDto.class);
    }

    @Transactional
    public Long deleteUserInfo(Long userId) {
        User findUser = findUsersById(userId);
        userRepository.delete(findUser);
        return userId;
    }
}
