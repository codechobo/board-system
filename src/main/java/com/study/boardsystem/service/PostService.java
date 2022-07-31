package com.study.boardsystem.service;

import com.study.boardsystem.domain.*;
import com.study.boardsystem.domain.type.Rank;
import com.study.boardsystem.exception.NotFoundEntityException;
import com.study.boardsystem.exception.code.CommonErrorCode;
import com.study.boardsystem.web.dto.post_dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.study.boardsystem.service
 * fileName       : PostService
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final SearchPostRepository searchPostRepository;

    @Transactional
    public PostSaveResponseDto savePost(PostSaveRequestDto postSaveRequestDto) {
        Member member = memberRepository.findByNickname(postSaveRequestDto.getNickname())
                .orElseThrow(() -> new NotFoundEntityException(CommonErrorCode.NOT_FOUND_ENTITY));
        member.addWriteCount();

        if (member.getWriteCount() >= 5) {
            member.joinRank(Rank.NORMAL);
        }

        Post post = Post.createPost(
                postSaveRequestDto.getTitle(),
                postSaveRequestDto.getDescription(),
                member);

        postRepository.save(post);

        return PostSaveResponseDto.toMapper(post, member.getNickname());
    }

    public PostSaveResponseDto findByIdPost(Long postId) {
        Post post = getEntity(postId);

        return PostSaveResponseDto.builder()
                .nickname(post.getMember().getNickname())
                .title(post.getTitle())
                .description(post.getDescription())
                .build();

    }

    @Transactional
    public void removePost(Long postId) {
        Post entity = getEntity(postId);
        postRepository.delete(entity);
    }

    @Transactional
    public void updateTitleAndDescription(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post entity = getEntity(postId);
        entity.updateTitle(postUpdateRequestDto.getTitle());
        entity.updateDescription(postUpdateRequestDto.getDescription());
    }

    private Post getEntity(Long postId) {
        return searchPostRepository.findById(postId)
                .orElseThrow(() -> new NotFoundEntityException(CommonErrorCode.NOT_FOUND_ENTITY));
    }


    public List<PostSearchNameResponseDto> findByTitlePost(String title) {
        if (title.equals("")) {
            return searchPostRepository.findAll().stream()
                    .map(post -> PostSearchNameResponseDto.builder()
                            .nickname(post.getMember().getNickname())
                            .title(post.getTitle())
                            .createDateTime(post.getCreateDateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return searchPostRepository.findByTitleContaining(title).stream()
                .map(post -> PostSearchNameResponseDto.builder()
                        .nickname(post.getMember().getNickname())
                        .title(post.getTitle())
                        .createDateTime(post.getCreateDateTime())
                        .build())
                .collect(Collectors.toList());
    }


}
