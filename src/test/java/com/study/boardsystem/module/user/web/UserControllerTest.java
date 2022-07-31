package com.study.boardsystem.module.user.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.module.user.domain.type.Address;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import com.study.boardsystem.module.user.web.dto.UserSaveRequestDto;
import com.study.boardsystem.module.user.web.dto.UserSaveResponseDto;
import com.study.boardsystem.module.user.web.dto.UserUpdateRequestDto;
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

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : UserControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Transactional
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

        mockMvc.perform(post("/api/users")
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
        userRepository.save(userRequestDto.toEntity());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원 조회하기")
    void getUser_with_id() throws Exception {
        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User user = userRepository.save(userRequestDto.toEntity());

        UserSaveResponseDto userSaveResponseDto = modelMapper.map(user, UserSaveResponseDto.class);

        Long id = user.getId();
        mockMvc.perform(get("/api/users/" + id)
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
        User user = userRepository.save(userRequestDto.toEntity());
        UserSaveResponseDto userSaveResponseDto = modelMapper.map(user, UserSaveResponseDto.class);

        String dto = objectMapper.writeValueAsString(userSaveResponseDto);

        mockMvc.perform(get("/api/users")
                        .param("nicknameOrEmail", userRequestDto.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().json(dto));
    }

    @Test
    @DisplayName("회원 정보 수정")
    void updateUser() throws Exception {
        UserUpdateRequestDto userUpdateRequestDto = createUserUpdateRequestDto();

        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User saveUser = userRepository.save(userRequestDto.toEntity());

        String updateDto = objectMapper.writeValueAsString(userUpdateRequestDto);
        mockMvc.perform(put("/api/users/" + saveUser.getId())
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

    @Test
    @DisplayName("회원 정보 삭제")
    void deleteUser() throws Exception {
        UserSaveRequestDto userRequestDto = createUserRequestDto();
        User saveUser = userRepository.save(userRequestDto.toEntity());

        mockMvc.perform(delete("/api/users/" + saveUser.getId()))
                .andExpect(status().isOk());

        assertThatThrownBy(() -> userRepository.findById(saveUser.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }


    private UserUpdateRequestDto createUserUpdateRequestDto() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setNickname("pack");
        userUpdateRequestDto.setEmail("pack@naver.com");
        userUpdateRequestDto.setAddress(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("모르겠음")
                .build());
        return userUpdateRequestDto;
    }

    private UserSaveRequestDto createUserRequestDto() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();
        userSaveRequestDto.setName("lee");
        userSaveRequestDto.setNickname("packman");
        userSaveRequestDto.setEmail("packman@naver.com");
        userSaveRequestDto.setPassword("hello1234");
        userSaveRequestDto.setAddress(Address.builder()
                .city("서울")
                .street("어딘가")
                .zipcode("모르겠음")
                .build());
        return userSaveRequestDto;
    }

}