package com.study.boardsystem.domain;

import com.study.boardsystem.domain.type.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : PostTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */
class PostTest {

    @Test
    @DisplayName("게시판 생성")
    void createPost() {
        Member member = Member.builder()
                .name("이기영")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build())
                .build();

        String title = "검정고무신이 재밌다";
        Post post = Post.createPost(
                title, "기영이 귀여워서 재밌지", member);

        assertThat(post.getMember()).isEqualTo(member);
        assertThat(post.getMember().getName()).isEqualTo(member.getName());
        assertThat(post.getTitle()).isEqualTo(title);
    }

}