package com.study.boardsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.domain.MemberExistsCheckRepository;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : MemberControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@WebMvcTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberExistsCheckRepository memberExistsCheckRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("member 회원 가입한다.")
    void saveMember() throws Exception {
        // given
        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .name("기영이")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지용?")
                        .build())
                .build();

        // when
        when(memberService.joinMember(any(MemberSaveRequestDto.class))).thenReturn(
                MemberSaveResponseDto.builder()
                        .member(memberSaveRequestDto.toEntity())
                        .build());

        // then
        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberSaveRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .json(objectMapper
                                .writeValueAsString(MemberSaveResponseDto.builder()
                                        .member(memberSaveRequestDto.toEntity())
                                        .build())));

        verify(memberService).joinMember(any(MemberSaveRequestDto.class));
    }

    @Test
    @DisplayName("member 조회 한다.")
    void findByIdMember() throws Exception {
        // given
        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .name("기영이")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지용?")
                        .build())
                .build();

        // when
        doReturn(MemberSaveResponseDto.builder()
                .member(memberSaveRequestDto.toEntity())
                .build())
                .when(memberService).findByIdEntity(1L);

        mockMvc.perform(get("/api/v1/members/" + 1L))
                .andDo(print())
                .andExpect(content()
                        .json(objectMapper
                                .writeValueAsString(MemberSaveResponseDto.builder()
                                        .member(memberSaveRequestDto.toEntity())
                                        .build())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(memberService).findByIdEntity(1L);
    }
}