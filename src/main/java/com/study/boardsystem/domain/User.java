package com.study.boardsystem.domain;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : User
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, length = 20)
    private String nickName;

    // TODO 패스워드 인코딩 해야함
    @Column(unique = true, nullable = false)
    private String password;

    private String city;

    private String address1;

    private String address2;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private boolean isJoin;


}
