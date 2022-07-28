package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.exception.NotFoundEntityException;
import com.study.boardsystem.exception.code.CommonErrorCode;
import com.study.boardsystem.web.dto.post.PostSaveRequestDto;
import com.study.boardsystem.web.dto.post.PostSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.study.boardsystem.service
 * fileName       : PostService
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostSaveResponseDto savePost(Long memberId, PostSaveRequestDto postSaveRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(CommonErrorCode.NOT_FOUND_ENTITY));

        Post post = Post.createPost(
                postSaveRequestDto.getTitle(),
                postSaveRequestDto.getDescription(),
                member);

        postRepository.save(post);

        return PostSaveResponseDto.toMapper(post, member.getNickname());
    }
}
