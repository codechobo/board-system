package com.study.boardsystem.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : PostTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */
class PostTest {

    @Test
    @DisplayName("게시판 생성")
    void PostTest() {
        String userName = "lee";
        String title = "분할운동의 좋은법?";

        Post post = new Post();
        post.setUserName(userName);
        post.setTitle(title);
        post.setDescription("회복시기에 근육의 성장이 된다.");

        assertNotNull(post);
        assertThat(post.getUserName()).isEqualTo(userName);
        assertThat(post.getTitle()).isEqualTo(title);
    }

}