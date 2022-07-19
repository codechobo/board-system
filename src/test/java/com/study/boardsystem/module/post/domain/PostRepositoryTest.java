package com.study.boardsystem.module.post.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : PostRepositoryTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void PostRepositoryTest() {
        Post post = Post.builder()
                .userName("기영이")
                .title("검정고무신")
                .description("재밌어요~")
                .build();

        Post save = postRepository.save(post);

        assertThat(save.getTitle()).isEqualTo(post.getTitle());
    }

}