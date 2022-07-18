package com.study.boardsystem.domain;

import com.study.boardsystem.module.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : PostTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */


@SpringBootTest
class PostTest {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Test
    @DisplayName("게시글 생성")
    void PostTest() {
        User user = User.builder()
                .nickname("검정")
                .password("검정12")
                .email("검정@naver.com")
                .name("이름")
                .city("서울")
                .address1("어디?")
                .address2("어딘가?")
                .build();

        em.persist(user);

        Post post = Post.builder()
                .title("타이틀이다.")
                .description("글 써라")
                .build();

        post.addUser(user);
        em.persist(post);


    }
}