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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "nickname",
                        "password",
                        "email",
                        "name"}
        )
})
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String nickname;

    // TODO 비밀번호 인코딩 필요
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "users_city")),
            @AttributeOverride(name = "street", column = @Column(name = "users_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "users_zipcode"))
    })
    private Address address;

    @Column
    private boolean isJoin;

    @Builder
    public User(String nickname, String password, String email, String name, Address address) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("주소를 생성할 수 없습니다.");
        }
        this.address = address;
    }

    // TODO 비밀번호 인코딩 필요
    public void updatePassword(String password) {
        this.password = password;
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
