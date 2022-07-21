package com.study.boardsystem.module.comment.web;

import com.study.boardsystem.module.comment.service.CommentService;
import com.study.boardsystem.module.comment.web.dto.CommentSaveRequestDto;
import com.study.boardsystem.module.comment.web.dto.CommentSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.study.boardsystem.module.comment.web
 * fileName       : CommentController
 * author         : tkdwk567@naver.com
 * date           : 2022/07/21
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentSaveResponseDto> createComment(
            @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        CommentSaveResponseDto commentSaveResponseDto = commentService.create(commentSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentSaveResponseDto);
    }
}
