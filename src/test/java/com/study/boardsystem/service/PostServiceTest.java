package com.study.boardsystem.service;

import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.web.dto.PostView;
import com.study.boardsystem.web.dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.PostSaveResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName    : com.study.boardsystem.service
 * fileName       : PostServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        Post post = PostSaveRequestDto.builder()
                .userName("기영이")
                .title("검정고무신")
                .description("검정고무신 완전 재밌지~~~")
                .build()
                .toEntity();

        postRepository.save(post);
    }

    @Test
    @DisplayName("게시판을 생성한다.")
    void createPost() {
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .userName("기철이")
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
                .userName("기철이")
                .title("검정고무신")
                .description("검정고무신 완전 재밌어요~~~")
                .build();

        PostSaveResponseDto dto = postService.create(postSaveRequestDto);

        // when
        List<PostView> result = postRepository.findByName("기철이");

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTitle()).isEqualTo(postSaveRequestDto.getTitle());
    }
}