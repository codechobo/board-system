package com.study.boardsystem.web;

import com.study.boardsystem.service.PostService;
import com.study.boardsystem.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostController
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<Long> createPost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        Long id = postService.create(postSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

}
