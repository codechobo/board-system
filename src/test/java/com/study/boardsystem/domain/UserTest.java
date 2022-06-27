package com.study.boardsystem.domain;

import com.study.boardsystem.module.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

class UserTest {

    @Test
    @DisplayName("사용자 객체 생성")
    void UserTest() {
        String name = "lee";
        String nickName = "뚱이";
        String password = "hello1234";

        User user = new User();
        user.setName(name);
        user.setNickname(nickName);
        user.setPassword(password);
        user.setJoin(true);

        assertNotNull(user);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
    }


}