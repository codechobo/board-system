package com.study.boardsystem.domain;

import com.study.boardsystem.module.user.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : Post
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String description;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;


    // 연관관계 메서드
    public void addUser(User user) {
        if (this.user != null) {
            this.user.getPosts().remove(this);
        }

        this.user = user;
        user.addPost(this);
    }
}
