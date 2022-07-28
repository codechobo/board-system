package com.study.boardsystem.web;

import com.study.boardsystem.service.PostService;
import com.study.boardsystem.web.dto.post.PostSaveResponseDto;
import com.study.boardsystem.web.dto.post.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostController
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> createPost(
            @Valid @RequestBody PostSaveRequestDto postSaveRequestDto) {
        PostSaveResponseDto postSaveResponseDto = postService.savePost(postSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postSaveResponseDto);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostSaveResponseDto> getPost(
            @PathVariable("id") Long postId) {
        PostSaveResponseDto postSaveResponseDto = postService.findByIdPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postSaveResponseDto);
    }

}
