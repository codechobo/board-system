package com.study.boardsystem.module.user.service;

import com.study.boardsystem.module.user.domain.type.Address;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import com.study.boardsystem.module.user.web.dto.UserSaveRequestDto;
import com.study.boardsystem.module.user.web.dto.UserSaveResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName    : com.study.boardsystem.module.user.service
 * fileName       : UserServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before() {
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .name("뚱이")
                .email("뚱@naver.com")
                .nickname("불가사리")
                .password("뚱이12")
                .address(Address.builder()
                        .city("바다")
                        .street("비키니시티")
                        .zipcode("바위")
                        .build())
                .build();

        userRepository.save(userSaveRequestDto.toEntity());
    }

    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void join() {
        // given
        UserSaveRequestDto userSaveDto = createUserSaveDto();

        // when
        Long join = userService.join(userSaveDto);

        // then
        User user = userRepository.findByNickname(userSaveDto.getNickname()).orElseThrow();
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(userSaveDto.getName());
        assertThat(user.getEmail()).isEqualTo(userSaveDto.getEmail());
        assertThat(user.isJoin()).isTrue();
    }

    @Test
    @DisplayName("회원 가입 실패 -> 이미 존재하는 닉네임 정보입니다.")
    void join_fail_with_nickname() {
        UserSaveRequestDto userSaveDto = createUserSaveDto();
        userSaveDto.setNickname("불가사리");

        assertThatThrownBy(() -> userService.join(userSaveDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 닉네임 정보입니다.");
    }

    @Test
    @DisplayName("회원 가입 실패 -> 이미 존재하는 이메일 정보입니다.")
    void join_fail_with_email() {
        UserSaveRequestDto userSaveDto = createUserSaveDto();
        userSaveDto.setEmail("뚱@naver.com");

        assertThatThrownBy(() -> userService.join(userSaveDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 이메일 정보입니다.");
    }

    @Test
    @DisplayName("id로 회원 찾기")
    void findUser_with_id() {
        UserSaveResponseDto user = userService.findUser(1L);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("뚱이");
    }

    private UserSaveRequestDto createUserSaveDto() {
        return UserSaveRequestDto.builder()
                .name("스폰지밥")
                .email("스폰지밥@naver.com")
                .nickname("스폰지")
                .password("스폰지1234")
                .address(Address.builder()
                        .city("바다")
                        .street("비키니시티")
                        .zipcode("파인애플")
                        .build())
                .build();
    }


}