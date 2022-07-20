package com.study.boardsystem.module.post.service;

import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName    : com.study.boardsystem.module.post.service
 * fileName       : PostServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        Post post1 = PostSaveRequestDto.builder()
                .userNickname("기영이")
                .title("검정고무신1")
                .description("검정고무신1 완전 재밌지~~~")
                .build()
                .toEntity();

        Post post2 = PostSaveRequestDto.builder()
                .userNickname("기철이")
                .title("검정고무신2")
                .description("검정고무신2 완전 재밌지~~~")
                .build()
                .toEntity();

        postRepository.saveAll(List.of(post1, post2));
    }

    @AfterEach
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판을 생성한다.")
    void createPost() {
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .userNickname("기철이")
                .title("검정고무신")
                .description("검정고무신 완전 재밌어요~~~")
                .build();
        PostSaveResponseDto dto = postService.create(postSaveRequestDto);

        assertNotNull(dto);
        assertThat(dto.getTitle()).isEqualTo(postSaveRequestDto.getTitle());
    }

    @Test
    @DisplayName("게시판 조회한다.")
    void findPost() {
        // given
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .userNickname("기철이")
                .title("검정고무신")
                .description("검정고무신 완전 재밌어요~~~")
                .build();
        PostSaveResponseDto dto = postService.create(postSaveRequestDto);

        // when
        List<PostFindResponseDto> result = postRepository.findByName("기철이");

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTitle()).isEqualTo(postSaveRequestDto.getTitle());
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void findPostList() {
        List<Post> posts = postRepository.findAll();

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(2);
        assertThat(posts.get(0).getUserNickname()).isEqualTo("기영이");
    }

    @Test
    @DisplayName("게시글 삭제")
    void deletePost() {
        // given
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .userNickname("기영 어머니")
                .title("검정고무신")
                .description("검정고무신 완전 재밌어요~~~")
                .build();
        PostSaveResponseDto dto = postService.create(postSaveRequestDto);

        // when
        postService.deleteByIdPost(3L);

        // then
        assertThatThrownBy(() -> postRepository.findById(3L)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 아이디입니다.")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재 하지 않는 아이디입니다.");
    }

    @Test
    @DisplayName("게시글 업데이트")
    void updateByIdPost() {
        // given
        Post updateBeforeEntity = postRepository.findById(1L).get();

        String updateBeforeTitle = updateBeforeEntity.getTitle();
        String updateBeforeDescription = updateBeforeEntity.getDescription();

        // when
        PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
                .title("스폰지밥")
                .description("검정고무신 보단 스폰지밥이 더 재밌어!")
                .build();
        postService.updateByIdPost(1L, postUpdateRequestDto);

        // then
        Post updateAfterEntity = postRepository.findById(1L).get();

        assertNotEquals(updateAfterEntity.getTitle(), updateBeforeTitle);
        assertNotEquals(updateAfterEntity.getDescription(), updateBeforeDescription);
        assertThat(updateAfterEntity.getTitle()).isEqualTo(postUpdateRequestDto.getTitle());
        assertThat(updateAfterEntity.getDescription()).isEqualTo(postUpdateRequestDto.getDescription());
    }
}