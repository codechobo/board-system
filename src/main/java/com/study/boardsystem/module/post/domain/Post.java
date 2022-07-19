package com.study.boardsystem.module.post.domain;

import com.study.boardsystem.module.comment.domain.Comment;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
@NoArgsConstructor
public class Post {

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

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

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
