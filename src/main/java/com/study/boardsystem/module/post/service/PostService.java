package com.study.boardsystem.module.post.service;

import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.study.boardsystem.module.post.service
 * fileName       : PostService
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostSaveResponseDto create(PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.save(postSaveRequestDto.toEntity());
        return PostSaveResponseDto.builder().post(post).build();
    }

    public List<PostFindResponseDto> findByNamePosts(String userName) {
        return postRepository.findByName(userName);
    }

    @Transactional
    public void deleteByIdPost(Long postId) {
        Post post = getEntity(postId);
        postRepository.delete(post);
    }

    @Transactional
    public PostSaveResponseDto updateByIdPost(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = getEntity(postId);
        post.updateEntity(postUpdateRequestDto);
        return PostSaveResponseDto.builder().post(post).build();
    }

    private Post getEntity(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }
}
