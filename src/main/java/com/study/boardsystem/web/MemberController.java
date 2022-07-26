package com.study.boardsystem.web;

import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.web.dto.MemberSaveResponseDto;
import com.study.boardsystem.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
