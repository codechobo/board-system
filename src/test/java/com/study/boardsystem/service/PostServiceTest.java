package com.study.boardsystem.service;

import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.MemberRepository;
import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.web.dto.post.PostSaveRequestDto;
import com.study.boardsystem.web.dto.post.PostSaveResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.study.boardsystem.service
 * fileName       : PostServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Post 생성한다")
    void savePost() {
        // given
        Member member = createMember();
        member.setId(1L);

        String title = "검정고무신";
        String description = "재밌지";

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .nickname(member.getNickname())
                .title(title)
                .description(description)
                .build();

        Post post = Post.createPost(title, description, member);

        when(memberRepository.findByNickname(anyString())).thenReturn(Optional.of(member));
        when(postRepository.save(any())).thenReturn(post);

        // when
        PostSaveResponseDto postSaveResponseDto = postService.savePost(postSaveRequestDto);

        assertThat(postSaveResponseDto.getNickname()).isEqualTo(member.getNickname());
        assertThat(postSaveResponseDto.getTitle()).isEqualTo(post.getTitle());
        assertThat(postSaveResponseDto.getDescription()).isEqualTo(post.getDescription());
    }

    private Member createMember() {
        return Member.builder()
                .name("이기영")
                .nickname("까까머리")
                .password("test1234")
                .email("기영@naver.com")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build())
                .build();
    }

}