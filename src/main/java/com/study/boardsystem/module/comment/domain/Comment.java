package com.study.boardsystem.module.comment.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.module.comment.domain
 * fileName       : Comment
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userNickname;

    @Column(nullable = false)
    private String content;

    @Column
    private Long postId;

    @Builder
    public Comment(String userNickname, String content, Long postId) {
        this.userNickname = userNickname;
        this.content = content;
        this.postId = postId;
    }

    public void addPostsId(Long postsId) {
        this.postId = postsId;
    }
}
