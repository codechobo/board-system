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
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title; // 제목

    @Lob
    @Column(name = "DESCRIPTION")
    private String description; // 내용

    @Column(name = "AUTHOR")
    private String author; // 글쓴이


    private List<Comment> comments = new ArrayList<>();

    @Column(name = "HITS")
    private int hits; // 조회수

    @Builder
    public Post(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
