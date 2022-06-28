package com.study.boardsystem.module.user.domain;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;

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

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    // TODO 비밀번호 인코딩 필요
    @Column(nullable = false, unique = true)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    private String city;

    private String address1;

    private String address2;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private boolean isJoin;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    // TODO 비밀번호 인코딩 필요
    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateAddress(String city, String address1, String address2) {
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
    }

    public void createCheckJoinAndCreateDateTime(LocalDateTime createDateTime, boolean isJoin) {
        this.createDateTime = createDateTime;
        this.isJoin = isJoin;
    }

}
