package com.study.boardsystem.service;

import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.web.dto.PostFindResponseDto;
import com.study.boardsystem.web.dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.PostSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.study.boardsystem.service
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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        postRepository.delete(post);
    }
}
