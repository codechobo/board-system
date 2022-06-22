package com.study.boardsystem.domain;

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
        User user = modelMapper.map(userSaveRequestDto, User.class);
        user.createCheckJoinAndCreateDateTime(LocalDateTime.now(), true);
        userRepository.save(user);
        return user.getId();
    }


}
