package com.study.boardsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : Comment
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Getter
@Table(name = "COMMENTS")
@NoArgsConstructor
@Entity
public class Comment extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTS_ID")
    private Long id;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBERS_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @Builder
    public Comment(String content, Member member, Post post) {
        this.content = content;
        this.member = member;
        this.post = post;
    }

    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member Argument Exception!!");
        }
        this.member = member;
    }

    public void addPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Member Argument Exception!!");
        }
        this.post = post;
        post.addComment(this);
    }

    public void addCommentChildren(Comment children) {
        this.children.add(children);
        this.parent = this;
    }

}
