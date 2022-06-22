package com.study.boardsystem.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.web.dto.UserSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Autowired
    ModelMapper modelMapper;

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void createUser_success() throws Exception {
        UserSaveRequestDto userSaveRequestDto = createUserRequestDto();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSaveRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        User user = userRepository.findByNickname(userSaveRequestDto.getNickname())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        assertNotNull(user);
        assertThat(user.getName()).isEqualTo(userSaveRequestDto.getName());
    }

    @Test
    @DisplayName("회원가입 실패")
    void createUser_duplicationNickName_fail() throws Exception {
        UserSaveRequestDto userRequestDto = createUserRequestDto();
        userRepository.save(modelMapper.map(userRequestDto, User.class));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    private UserSaveRequestDto createUserRequestDto() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
        userSaveRequestDto.setName("lee");
        userSaveRequestDto.setNickname("packman");
        userSaveRequestDto.setEmail("packman@naver.com");
        userSaveRequestDto.setPassword("hello1234");
        userSaveRequestDto.setCity("서울");
        userSaveRequestDto.setAddress1("어디?");
        userSaveRequestDto.setAddress2("여기");
        return userSaveRequestDto;
    }

}