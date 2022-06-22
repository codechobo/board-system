package com.study.boardsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.domain.User;
import com.study.boardsystem.domain.UserRepository;
import com.study.boardsystem.web.dto.UserSaveRequestDto;
import com.study.boardsystem.web.dto.UserSaveResponseDto;
import com.study.boardsystem.web.dto.UserUpdateRequestDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@SpringBootTest
@Transactional
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

    @Test
    @DisplayName("회원 조회하기")
    void getUser_with_id() throws Exception {
        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User user = userRepository.save(modelMapper.map(userRequestDto, User.class));

        UserSaveResponseDto userSaveResponseDto = modelMapper.map(user, UserSaveResponseDto.class);
        Long id = 1L;
        mockMvc.perform(get("/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userSaveResponseDto)));
    }

    @Test
    @DisplayName("이메일로 회원 조회하기")
    void getUser_with_nickname_email() throws Exception {
        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User user = userRepository.save(modelMapper.map(userRequestDto, User.class));
        UserSaveResponseDto userSaveResponseDto = modelMapper.map(user, UserSaveResponseDto.class);

        String dto = objectMapper.writeValueAsString(userSaveResponseDto);

        mockMvc.perform(get("/users")
                        .param("nicknameOrEmail", userRequestDto.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().json(dto));
    }

    @Test
    @DisplayName("회원 정보 수정")
    void updateUser() throws Exception {
        UserUpdateRequestDto userUpdateRequestDto = createUserUpdateRequestDto();

        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User saveUser = userRepository.save(modelMapper.map(userRequestDto, User.class));

        String updateDto = objectMapper.writeValueAsString(userUpdateRequestDto);
        mockMvc.perform(put("/users/" + saveUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        User findUser = userRepository.findById(saveUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 회원입니다."));

        assertNotNull(findUser);
        assertThat(findUser.getNickname()).isEqualTo(userUpdateRequestDto.getNickname());
        assertThat(findUser.getEmail()).isEqualTo(userUpdateRequestDto.getEmail());
    }

    private UserUpdateRequestDto createUserUpdateRequestDto() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setNickname("pack");
        userUpdateRequestDto.setEmail("pack@naver.com");
        userUpdateRequestDto.setCity("서울");
        userUpdateRequestDto.setAddress1("어딘가");
        userUpdateRequestDto.setAddress2("단지");
        return userUpdateRequestDto;
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