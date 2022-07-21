package com.study.boardsystem.module.post.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
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
@Table(name = "posts")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String description;

    @Builder
    public Post(String userNickname, String title, String description) {
        this.userNickname = userNickname;
        this.title = title;
        this.description = description;
    }

    public void updateEntity(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.description = postUpdateRequestDto.getDescription();
    }
}
