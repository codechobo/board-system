package com.study.boardsystem.web;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.web.dto.member_dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.member_dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.member_dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : MemberController
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> addMember(
            @Valid @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        MemberSaveResponseDto memberSaveResponseDto = memberService.joinMember(memberSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSaveResponseDto);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberSaveResponseDto> getMember(
            @PathVariable("id") Long membersId) {
        MemberSaveResponseDto memberSaveResponseDto = memberService.findByIdEntity(membersId);
        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberSaveResponseDto>> getMembers() {
        List<Member> list = memberRepository.findAll();
        List<MemberSaveResponseDto> result = list.stream().map(member ->
                MemberSaveResponseDto.builder()
                        .member(member)
                        .build()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberSaveResponseDto> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        MemberSaveResponseDto memberSaveResponseDto = memberService
                .updateAfterFindEntity(id, memberUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(
            @PathVariable("id") Long memberId) {
        memberService.removeMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
