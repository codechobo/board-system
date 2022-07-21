package com.study.boardsystem.module.user.domain;

import com.study.boardsystem.module.base.domain.BaseTimeEntity;
import com.study.boardsystem.module.user.domain.type.Address;
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
@Table(name = "USERS", uniqueConstraints = {
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
    @Column(name = "USERS_ID")
    private Long id;

    @Column(name = "NICKNAME", nullable = false, length = 20)
    private String nickname;

    // TODO 비밀번호 인코딩 필요
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "USERS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "USERS_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "USERS_ZIPCODE"))
    })
    private Address address;

    @Column(name = "IS_JOIN")
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
