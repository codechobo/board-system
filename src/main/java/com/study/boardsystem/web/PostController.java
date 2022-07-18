package com.study.boardsystem.web;

import com.study.boardsystem.service.PostService;
import com.study.boardsystem.web.dto.PostFindResponseDto;
import com.study.boardsystem.web.dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.PostSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> createPosts(
            @RequestBody PostSaveRequestDto postSaveRequestDto) {
        PostSaveResponseDto dto = postService.create(postSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/posts/{name}")
    public ResponseEntity<List<PostFindResponseDto>> searchByName(
            @Validated @PathVariable("name") String userName) {
        List<PostFindResponseDto> dtos = postService.findByNamePosts(userName);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
