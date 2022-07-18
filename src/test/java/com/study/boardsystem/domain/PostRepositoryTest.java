package com.study.boardsystem.domain;

import com.study.boardsystem.web.dto.PostView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.domain
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

        List<PostView> byName = postRepository.findByName(save.getUserName());



    }

}