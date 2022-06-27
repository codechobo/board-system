package com.study.boardsystem.module.user.domain;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    // TODO 패스워드 인코딩 해야함
    @Column(nullable = false, unique = true)
    private String password;

    private String city;

    private String address1;

    private String address2;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private boolean isJoin;

    public void createCheckJoinAndCreateDateTime(LocalDateTime createDateTime, boolean isJoin) {
        this.createDateTime = createDateTime;
        this.isJoin = isJoin;
    }

}
