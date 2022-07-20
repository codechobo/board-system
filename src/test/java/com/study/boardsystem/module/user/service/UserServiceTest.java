package com.study.boardsystem.module.user.service;

import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import com.study.boardsystem.module.user.web.dto.UserSaveRequestDto;
import com.study.boardsystem.module.user.web.dto.UserSaveResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName    : com.study.boardsystem.module.user.service
 * fileName       : UserServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

//    @BeforeEach
    public void before() {
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .name("뚱이")
                .email("뚱@naver.com")
                .nickname("불가사리")
                .password("뚱이12")
                .city("바다")
                .address1("돌")
                .address2("돌돌")
                .build();

        userRepository.save(modelMapper.map(userSaveRequestDto, User.class));
    }

//    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("model mapper test")
    void modelMapperTest() {
        UserSaveRequestDto userSaveDto = createUserSaveDto();
        User user = modelMapper.map(userSaveDto, User.class);

        assertNotNull(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
    }

    @Test
    @DisplayName("회원 가입 성공")
    void join() {
        // given
        UserSaveRequestDto userSaveDto = createUserSaveDto();

        // when
        Long join = userService.join(userSaveDto);

        // then
//        User user = userRepository.findByNickname(userSaveDto.getNickname()).orElseThrow();
//        assertThat(user).isNotNull();
//        assertThat(user.getName()).isEqualTo(userSaveDto.getName());
//        assertThat(user.getEmail()).isEqualTo(userSaveDto.getEmail());
//        assertThat(user.isJoin()).isTrue();
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
                .city("바다")
                .address1("파인애플")
                .address2("애플")
                .build();
    }


}