package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberExistsCheckRepository;
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
    private final MemberExistsCheckRepository memberExistsCheckRepository;

    @Transactional
    public MemberSaveResponseDto joinMember(MemberSaveRequestDto memberSaveRequestDto) {
        if (memberExistsCheckRepository.existsByName(memberSaveRequestDto.getName())) {
            throw new IllegalArgumentException("Duplication Name Error!!");
        }

        if (memberExistsCheckRepository.existsByNickname(memberSaveRequestDto.getNickname())) {
            throw new IllegalArgumentException("Duplication Nickname Error!!");
        }

        if (memberExistsCheckRepository.existsByEmail(memberSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("Duplication Email Error!!");
        }

        if (memberExistsCheckRepository.existsByPassword(memberSaveRequestDto.getPassword())) {
            throw new IllegalArgumentException("Duplication Password Error!!");
        }

        Member member = memberSaveRequestDto.toEntity();
        member.isJoin(true);
        memberRepository.save(member);
        return MemberSaveResponseDto.builder().member(member).build();
    }

    public MemberSaveResponseDto findByIdEntity(Long membersId) {
        Member member = memberRepository.findById(membersId)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Member!!"));
        return MemberSaveResponseDto.builder().member(member).build();
    }
}
