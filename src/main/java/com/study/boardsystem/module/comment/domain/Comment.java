package com.study.boardsystem.module.comment.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.module.comment.domain
 * fileName       : Comment
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */

@Getter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

}
