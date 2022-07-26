package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberExistsCheckRepository;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.study.boardsystem.service
 * fileName       : MemberServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberExistsCheckRepository memberExistsCheckRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("Member 생성이 된다.")
    void joinMember() {
        // given
        Address address = Address.builder()
                .city("서울")
                .street("어딘가")
                .zipcode("살겠지")
                .build();
        MemberSaveRequestDto memberSaveRequestDto = createMemberSaveRequestDto(
                "이기영",
                "까까머리",
                "기영@naver.com",
                "test1234",
                address);

        // when
        when(memberRepository.save(any(Member.class))).thenReturn(memberSaveRequestDto.toEntity());
        MemberSaveResponseDto memberSaveResponseDto = memberService.joinMember(memberSaveRequestDto);

        // then
        assertThat(memberSaveResponseDto).isNotNull();
        assertThat(memberSaveResponseDto.getAddress()).isEqualTo(memberSaveRequestDto.getAddress());
        assertThat(memberSaveResponseDto.getEmail()).isEqualTo(memberSaveRequestDto.getEmail());
    }

    @Test
    @DisplayName("Member 아이디(pk)로 조회 한다.")
    void MemberServiceTest() {
        // given
        Address address = Address.builder()
                .city("서울")
                .street("어딘가")
                .zipcode("살겠지")
                .build();
        MemberSaveRequestDto memberSaveRequestDto = createMemberSaveRequestDto(
                "이기영",
                "까까머리",
                "기영@naver.com",
                "test1234",
                address);

        given(memberRepository.save(any(Member.class)))
                .willReturn(memberSaveRequestDto.toEntity());
        Member saveMember = memberRepository.save(memberSaveRequestDto.toEntity());

        // when
        when(memberRepository.findById(any())).thenReturn(Optional.of(saveMember));
        MemberSaveResponseDto memberSaveResponseDto = memberService.findByIdEntity(saveMember.getId());

        // then
        assertNotNull(memberSaveResponseDto);
        assertThat(memberSaveResponseDto.getName()).isEqualTo(memberSaveRequestDto.getName());
        assertThat(memberSaveResponseDto.getNickname()).isEqualTo(memberSaveRequestDto.getNickname());
    }

    private MemberSaveRequestDto createMemberSaveRequestDto(String name, String nickname, String email, String password, Address address) {
        return MemberSaveRequestDto.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .address(address)
                .build();
    }


}