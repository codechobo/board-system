package com.study.boardsystem.module.comment.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.module.comment.domain
 * fileName       : Comment
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */

@Getter
@Entity
@Table(name = "comments")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

}
