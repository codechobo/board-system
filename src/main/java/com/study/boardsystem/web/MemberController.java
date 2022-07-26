package com.study.boardsystem.web;

import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> saveMember(
            @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        MemberSaveResponseDto memberSaveResponseDto = memberService.joinMember(memberSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSaveResponseDto);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberSaveResponseDto> findByIdMember(
            @PathVariable("id") Long membersId) {
        MemberSaveResponseDto memberSaveResponseDto = memberService.findByIdEntity(membersId);
        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }
}
