package com.study.boardsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.MemberUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : MemberControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

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

    @Test
    @DisplayName("Member 전제 조회")
    void getMembers() throws Exception {
        // given
        Member member = new Member("이기영",
                "까까머리",
                "기영@naver.com",
                "test1234",
                Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build());
        Member member1 = new Member("이기철",
                "콜라도둑",
                "기철@naver.com",
                "test12341234",
                Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build());

        doReturn(List.of(member, member1)).when(memberRepository).findAll();
        List<MemberSaveResponseDto> content = memberRepository.findAll()
                .stream()
                .map(m -> MemberSaveResponseDto.builder()
                        .member(m)
                        .build())
                .collect(Collectors.toList());

        // when && then
        mockMvc.perform(get("/api/v1/members"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(content)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Member 업데이트 한다")
    void updateMember() throws Exception {
        // given
        Member member = new Member("이기철",
                "콜라도둑",
                "기철@naver.com",
                "test12341234",
                Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build());
        given(memberRepository.save(any(Member.class)))
                .willReturn(member);
        Member saveMember = memberRepository.save(member);

        MemberUpdateRequestDto memberUpdateRequestDto = MemberUpdateRequestDto.builder()
                .email("기철2@naver.com")
                .nickname("라면도둑")
                .password("test123")
                .build();
        saveMember.updateNickname(memberUpdateRequestDto.getNickname());
        saveMember.updateEmail(memberUpdateRequestDto.getEmail());
        saveMember.updatePassword(memberUpdateRequestDto.getPassword());

        // when && then
        when(memberService.updateAfterFindEntity(anyLong(), any(MemberUpdateRequestDto.class)))
                .thenReturn(MemberSaveResponseDto.builder()
                        .member(saveMember)
                        .build());

        mockMvc.perform(put("/api/v1/members/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberUpdateRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value(memberUpdateRequestDto.getNickname()))
                .andExpect(jsonPath("$.email").value(memberUpdateRequestDto.getEmail()));
    }
}