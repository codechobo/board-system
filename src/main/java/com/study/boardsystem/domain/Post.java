package com.study.boardsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
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
    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member argument Exception!!");
        }
        this.member = member;
    }

    public static Post createPost(String title, String description, Member member) {
        Post post = Post.builder()
                .title(title)
                .description(description)
                .build();
        post.addMember(member);
        return post;
    }

}
