package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : MemberService
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponseDto joinMember(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = memberSaveRequestDto.toEntity();
        member.isJoin(true);
        memberRepository.save(member);
        return MemberSaveResponseDto.builder().member(member).build();
    }
}
