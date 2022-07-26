package com.study.boardsystem.domain;

import com.study.boardsystem.domain.type.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        Member member = createMember();
        String title = "검정고무신이 재밌다";
        Post post = createPost(member, title);

        assertThat(post.getMember()).isEqualTo(member);
        assertThat(post.getMember().getName()).isEqualTo(member.getName());
        assertThat(post.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("댓글 생성")
    void addComment() {
        Member postWriterMember = createMember();
        String title = "검정고무신이 재밌다";
        Post post = createPost(postWriterMember, title);

        Member commentWriterMember = createMember();
        Comment comment = Comment.builder()
                .post(post)
                .member(commentWriterMember)
                .content("인정 검정 고무신 완전 재밌음!!")
                .build();

        post.addComment(comment);

        assertThat(post.getComments().contains(comment)).isTrue();
    }

    @Test
    @DisplayName("대댓글 생성")
    void PostTest() {
        // 게시글 생성
        Member postWriterMember = createMember();
        String title = "검정고무신이 재밌다";
        Post post = createPost(postWriterMember, title);

        // 댓글 생성
        Member commentWriterMember = createMember();
        Comment comment = Comment.builder()
                .post(post)
                .member(commentWriterMember)
                .content("인정 검정 고무신 완전 재밌음!!")
                .build();
        post.addComment(comment);

        // 대댓글 생성
        Member commentWriterMember2 = createMember();
        Comment comment2 = Comment.builder()
                .post(post)
                .member(commentWriterMember2)
                .content("난 재미 없던데")
                .build();
        comment.addCommentChildren(comment2);

        post.getComments()
                .iterator().forEachRemaining(com ->
                        assertAll(
                                () -> assertThat(com.getChildren().contains(comment2)), // 대댓글 인스턴스 검증
                                () -> assertThat(com.getChildren().size()).isEqualTo(1), // 대댓글 갯수 검증
                                () -> assertThat(com.getChildren().get(0).getMember()).isEqualTo(commentWriterMember2), // 대댓글 작성한 회원 검증
                                () -> assertThat(com.getChildren().get(0).getContent()).isEqualTo("난 재미 없던데") // 대댓글 제목 검증
                        ));
    }


    private Post createPost(Member member, String title) {
        Post post = Post.createPost(
                title, "기영이 귀여워서 재밌지", member);
        return post;
    }

    private Member createMember() {
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
        return member;
    }

}