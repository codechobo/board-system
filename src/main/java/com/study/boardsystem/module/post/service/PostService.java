package com.study.boardsystem.module.post.service;

import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public PostSaveResponseDto create(Long usersId, PostSaveRequestDto postSaveRequestDto) {
        User user = userRepository.findById(usersId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = Post.builder()
                .userNickname(user.getNickname())
                .title(postSaveRequestDto.getTitle())
                .description(postSaveRequestDto.getDescription())
                .build();
        post.addUser(user);

        postRepository.save(post);
        return PostSaveResponseDto.builder().post(post).build();
    }

    public List<PostFindResponseDto> findByNamePosts(String userNickname) {
        return postRepository.findByNickname(userNickname);
    }

    @Transactional
    public void deleteByIdPost(Long postId) {
        postRepository.deleteById(postId);
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
