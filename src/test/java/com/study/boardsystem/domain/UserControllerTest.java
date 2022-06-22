package com.study.boardsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("회원가입 성공")
    void createUser() throws Exception {
        UserSaveRequestDto userSaveRequestDto = createUserRequestDto();

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(userSaveRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        User user = userRepository.findByNickName(userSaveRequestDto.getNickName())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        assertNotNull(user);
        assertThat(user.getName()).isEqualTo(userSaveRequestDto.getName());
    }

    private UserSaveRequestDto createUserRequestDto() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
        userSaveRequestDto.setName("lee");
        userSaveRequestDto.setPassword("hello1234");
        userSaveRequestDto.setNickName("packman");
        return userSaveRequestDto;
    }

}