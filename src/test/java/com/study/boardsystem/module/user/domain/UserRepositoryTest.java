package com.study.boardsystem.module.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.module.user.domain
 * fileName       : UserRepositoryTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원 가입")
    void UserRepositoryTest() {
        User user = User.builder()
                .name("스폰지밥")
                .password("스폰지밥12")
                .email("스폰지밥@naver.com")
                .nickname("스폰지")
                .city("서울")
                .address1("어딘가")
                .address2("안알려줌")
                .build();

        User save = userRepository.save(user);

        User findUser = userRepository.findByNickname(user.getNickname()).get();

        assertThat(findUser.getEmail()).isEqualTo(user.getEmail());
    }

}