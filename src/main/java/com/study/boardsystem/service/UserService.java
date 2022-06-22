package com.study.boardsystem.service;

import com.study.boardsystem.domain.User;
import com.study.boardsystem.domain.UserRepository;
import com.study.boardsystem.web.dto.UserSaveRequestDto;
import com.study.boardsystem.web.dto.UserSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.domain
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
        if (userRepository.existsByNicknameOrEmail(userSaveRequestDto.getNickname(), userSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 정보입니다,");
        }

        User user = modelMapper.map(userSaveRequestDto, User.class);
        user.createCheckJoinAndCreateDateTime(LocalDateTime.now(), true);

        userRepository.save(user);
        return user.getId();
    }

    public UserSaveResponseDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 회원입니다."));
        return modelMapper.map(user, UserSaveResponseDto.class);
    }
}