package com.study.boardsystem.service;

import com.study.boardsystem.domain.*;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.web.dto.post_dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.post_dto.PostSaveResponseDto;
import com.study.boardsystem.web.dto.post_dto.PostSearchNameResponseDto;
import com.study.boardsystem.web.dto.post_dto.PostUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    @Mock
    private SearchPostRepository searchPostRepository;

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

        given(memberRepository.findByNickname(anyString())).willReturn(Optional.of(member));
        given(postRepository.save(any(Post.class))).willReturn(post);

        // when
        PostSaveResponseDto postSaveResponseDto = postService.savePost(postSaveRequestDto);

        assertThat(postSaveResponseDto.getNickname()).isEqualTo(member.getNickname());
        assertThat(postSaveResponseDto.getTitle()).isEqualTo(post.getTitle());
        assertThat(postSaveResponseDto.getDescription()).isEqualTo(post.getDescription());
    }

    @Test
    @DisplayName("Post 조회 한다")
    void findByIdPost() {
        // given
        Member member = createMember();
        Post post = Post.createPost(
                "검정고무신 재미짐",
                "라면 먹방 너무 재밌음",
                member);

        when(searchPostRepository.findById(anyLong()))
                .thenReturn(Optional.of(post));

        // when
        PostSaveResponseDto postSaveResponseDto = postService
                .findByIdPost(1L);

        // then
        assertEquals(member.getNickname(), postSaveResponseDto.getNickname());
        assertEquals(post.getTitle(), postSaveResponseDto.getTitle());
        assertEquals(post.getDescription(), postSaveResponseDto.getDescription());
    }

    @Test
    @DisplayName("Post Title 빈칸 검색 -> 전체 리스트 가져온다")
    void findByTitlePost_with_findAll() {
        // given
        Member member = createMember();
        Post post = Post.createPost("제목", "글 내용", member);
        List<Post> posts = List.of(post, post);

        given(searchPostRepository.findAll()).willReturn(posts);

        // when
        String title = "";
        List<PostSearchNameResponseDto> result = postService.findByTitlePost(title);

        // then
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(2);
        assertAll(
                () -> assertThat(result.get(0).getTitle()).isEqualTo(post.getTitle()),
                () -> assertThat(result.get(1).getTitle()).isEqualTo(post.getTitle())
        );

        verify(searchPostRepository).findAll();
    }

    @Test
    @DisplayName("Post Title 앞글자 검색 -> 검색에 맞는 title 리스트 가져온다")
    void PostServiceTest_with_findByTitleContaining() {
        // given
        Member member = createMember();
        Post post = Post.createPost("원 피 스", "글 내용", member);
        List<Post> posts = List.of(post);

        given(searchPostRepository.findByTitleContaining(anyString())).willReturn(posts);

        // when
        String title = "원피";
        List<PostSearchNameResponseDto> result = postService.findByTitlePost(title);

        // then
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
        assertAll(
                () -> assertThat(result.get(0).getTitle()).isEqualTo(post.getTitle())
        );

        verify(searchPostRepository).findByTitleContaining(anyString());
    }

    @Test
    @DisplayName("Post 업데이트 된다")
    void updateTitleAndDescription() {
        // given
        Member member = createMember();
        Post post = Post.createPost("제목", "글내용", member);

        PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
                .title("스폰지밥").description("스폰지밥 꿀잼").build();

        PostService mock = mock(PostService.class);
        mock.updateTitleAndDescription(1L, postUpdateRequestDto);

        verify(mock).updateTitleAndDescription(1L, postUpdateRequestDto);

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