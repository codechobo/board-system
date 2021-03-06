package com.study.boardsystem.domain;

import com.study.boardsystem.domain.base.TimeEntity;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.domain.type.Rank;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : Member
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Getter
@Setter
@DynamicUpdate
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "MEMBERS", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "NAME",
                        "NICKNAME",
                        "EMAIL",
                        "PASSWORD"})})
@Entity
public class Member extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name; // 이름

    @Column(name = "NICKNAME", nullable = false, length = 50)
    private String nickname; // 닉네임

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email; // 이메일

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password; // 비밀번호 인코딩 해야함

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "city", column = @Column(name = "MEMBERS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "MEMBERS_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "MEMBERS_ZIPCODE")
            )})
    private Address address; // 주소

    @Column(name = "IS_JOIN")
    private boolean isJoin; // 가입 여부

    // 게시판 글 5번 이상 쓰면 새싹등급에서 일반등급으로 변환
    @Column(name = "RANK", nullable = false)
    private Rank rank;

    @Transient
    private int writeCount; // 게시판 쓰기 횟수

    @Builder
    public Member(String name, String nickname, String email, String password, Address address) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public void isJoin(boolean join) {
        this.isJoin = join;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void joinRank(Rank rank) {
        this.rank = rank;
    }

    public void addWriteCount() {
        int currentWriteCount = getWriteCount();
        this.writeCount = currentWriteCount + 1;
    }
}
