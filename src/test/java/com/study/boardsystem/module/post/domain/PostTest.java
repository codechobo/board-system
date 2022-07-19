package com.study.boardsystem.module.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : PostTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */
class PostTest {

    @Test
    @DisplayName("post 객체 생성")
    void PostTest() {
        Post post = Post.builder()
                .userName("스폰지밥")
                .title("제목")
                .description("글 내용 길게~~~")
                .build();

        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo("제목");
    }
}