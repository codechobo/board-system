package com.study.boardsystem.module.user.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : User
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString(exclude = "password", callSuper = true)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
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

    @Column(length = 20)
    private String city;

    @Column(length = 20)
    private String address1;

    @Column(length = 20)
    private String address2;

    @Column
    private boolean isJoin;

    @Builder
    public User(String nickname, String password, String email, String name, String city, String address1, String address2) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.name = name;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
    }

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

    public void joinCheck(boolean isJoin) {
        this.isJoin = isJoin;
    }

    public void emailCheck(String email) {
        if (!this.email.equals(email)) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
    }

}
