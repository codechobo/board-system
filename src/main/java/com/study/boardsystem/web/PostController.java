package com.study.boardsystem.web;

import com.study.boardsystem.domain.PostRepository;
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
import java.util.stream.Collectors;

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
    private final PostRepository postRepository;

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

    @GetMapping("/posts/list")
    public ResponseEntity<List<PostSaveResponseDto>> list() {
        List<PostSaveResponseDto> posts = postRepository.findAll().stream()
                .map(p -> PostSaveResponseDto.builder().post(p).build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(
            @PathVariable("id") Long postId) {
        postService.deleteByIdPost(1L);
    }
}
