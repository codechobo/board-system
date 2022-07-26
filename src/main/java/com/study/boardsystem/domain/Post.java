package com.study.boardsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : Post
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Getter
@NoArgsConstructor
@Table(name = "POSTS")
@Entity
public class Post extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title; // 제목

    @Lob
    @Column(name = "DESCRIPTION")
    private String description; // 내용

    @ManyToOne
    @JoinColumn(name = "MEMBERS_ID")
    private Member member;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "HITS")
    private int hits; // 조회수

    @Builder
    public Post(String title, String description, String author) {
        this.title = title;
        this.description = description;
    }
}
