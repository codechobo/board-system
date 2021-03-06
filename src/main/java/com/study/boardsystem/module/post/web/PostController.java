package com.study.boardsystem.module.post.web;

import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.service.PostService;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.study.boardsystem.module.post.web
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

    @PostMapping("/{id}/posts")
    public ResponseEntity<PostSaveResponseDto> createPosts(
            @PathVariable(name = "id") Long usersId,
            @RequestBody PostSaveRequestDto postSaveRequestDto) {
        PostSaveResponseDto dto = postService.create(usersId, postSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostFindResponseDto>> searchByName(
            @Validated @RequestParam(name = "nickname") String userNickname) {
        List<PostFindResponseDto> dtos = postService.findByNamePosts(userNickname);
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
    public void deletePost(@PathVariable("id") Long postId) {
        postService.deleteByPost(postId);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostSaveResponseDto> updatePost(
            @PathVariable("id") Long postId,
            @Validated @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        PostSaveResponseDto postSaveResponseDto =
                postService.updateByIdPost(postId, postUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(postSaveResponseDto);
    }

}
