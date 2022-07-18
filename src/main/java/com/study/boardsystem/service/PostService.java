package com.study.boardsystem.service;

import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.web.dto.PostView;
import com.study.boardsystem.web.dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.PostSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<PostView> findPost(String userName) {
        return postRepository.findByName(userName);
    }
}
