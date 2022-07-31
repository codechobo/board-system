package com.study.boardsystem.module.comment.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.user.domain.User;
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
@Table(name = "COMMENTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTS_ID")
    private Long id;

    @Column(name = "USER_NICKNAME")
    private String userNickname;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder
    public Comment(String userNickname, String content) {
        this.userNickname = userNickname;
        this.content = content;
    }

    public void addUser(User user) {
        this.user = user;
    }

    public void addPost(Post post) {
        this.post = post;
    }
}
