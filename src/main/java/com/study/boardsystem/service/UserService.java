package com.study.boardsystem.service;

import com.study.boardsystem.domain.User;
import com.study.boardsystem.domain.UserRepository;
import com.study.boardsystem.web.dto.UserSaveRequestDto;
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
        if (userRepository.existsByNickName(userSaveRequestDto.getNickName())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User user = modelMapper.map(userSaveRequestDto, User.class);
        user.createCheckJoinAndCreateDateTime(LocalDateTime.now(), true);

        userRepository.save(user);
        return user.getId();
    }


}
