package com.study.boardsystem.module.post.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import com.study.boardsystem.module.user.domain.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : Post
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Getter
@Entity
@DynamicUpdate
@Table(name = "POSTS")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(name = "USER_NICKNAME")
    private String userNickname;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder
    public Post(String userNickname, String title, String description) {
        this.userNickname = userNickname;
        this.title = title;
        this.description = description;
    }

    public static Post createPost(String userNickname, String title, String description, User user) {
        Post post = Post.builder()
                .userNickname(userNickname)
                .title(title)
                .description(description)
                .build();
        post.addUser(user);
        return post;
    }

    public void addUser(User user) {
        this.user = user;
    }

    public void deleteUser(User user) {
        if (this.user != user) {
            throw new IllegalArgumentException("삭제할 수 없습니다.");
        } else {
            User removeUser = this.user;
            this.user = null;
        }
    }

    public void updateEntity(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.description = postUpdateRequestDto.getDescription();
    }
}
