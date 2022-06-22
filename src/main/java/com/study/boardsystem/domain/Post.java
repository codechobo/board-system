package com.study.boardsystem.domain;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : Post
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    private String description;


}
