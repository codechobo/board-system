package com.study.boardsystem.module.post.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import com.study.boardsystem.module.comment.domain.Comment;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long id;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String description;

    @OneToMany
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public Post(String userName, String title, String description) {
        this.userName = userName;
        this.title = title;
        this.description = description;
    }

    public void updateEntity(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.description = postUpdateRequestDto.getDescription();
    }
}
