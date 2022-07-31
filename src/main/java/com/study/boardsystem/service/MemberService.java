package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberExistsCheckRepository;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.domain.type.Rank;
import com.study.boardsystem.exception.DuplicationException;
import com.study.boardsystem.exception.NotFoundEntityException;
import com.study.boardsystem.exception.code.CommonErrorCode;
import com.study.boardsystem.web.dto.member_dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.member_dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.member_dto.MemberUpdateRequestDto;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberExistsCheckRepository memberExistsCheckRepository;

    @Transactional
    public MemberSaveResponseDto joinMember(MemberSaveRequestDto memberSaveRequestDto) {
        if (memberExistsCheckRepository.existsByName(memberSaveRequestDto.getName())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        if (memberExistsCheckRepository.existsByNickname(memberSaveRequestDto.getNickname())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        if (memberExistsCheckRepository.existsByEmail(memberSaveRequestDto.getEmail())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        if (memberExistsCheckRepository.existsByPassword(memberSaveRequestDto.getPassword())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        Member member = memberSaveRequestDto.toEntity();
        member.isJoin(true);
        member.joinRank(Rank.BASIC);

        memberRepository.save(member);
        return MemberSaveResponseDto.builder().member(member).build();
    }

    public MemberSaveResponseDto findByIdEntity(Long membersId) {
        Member member = getEntity(membersId);
        return MemberSaveResponseDto.builder().member(member).build();
    }

    @Transactional
    public MemberSaveResponseDto updateAfterFindEntity(Long membersId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member entity = getEntity(membersId);

        if (entity.getNickname().equals(memberUpdateRequestDto.getNickname())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        if (entity.getEmail().equals(memberUpdateRequestDto.getEmail())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        if (entity.getPassword().equals(memberUpdateRequestDto.getPassword())) {
            throw new DuplicationException(CommonErrorCode.DUPLICATION_FIELD_VALUE);
        }

        entity.updateEmail(memberUpdateRequestDto.getEmail());
        entity.updateNickname(memberUpdateRequestDto.getNickname());
        entity.updatePassword(memberUpdateRequestDto.getPassword());

        return MemberSaveResponseDto.builder()
                .member(entity)
                .build();
    }

    @Transactional
    public void removeMember(Long memberId) {
        Member entity = getEntity(memberId);
        memberRepository.delete(entity);
    }

    private Member getEntity(Long membersId) {
        return memberRepository.findById(membersId)
                .orElseThrow(() -> new NotFoundEntityException(CommonErrorCode.NOT_FOUND_ENTITY));
    }
}
