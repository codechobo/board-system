package com.study.boardsystem.module.user.domain;

import com.study.boardsystem.domain.Post;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
@ToString
public class User {

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

    private String city;

    private String address1;

    private String address2;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    private boolean isJoin;


    // 연관관계 메서드
    public void addPost(Post post) {
        this.posts.add(post);
        post.addUser(this);
    }


    // 업데이트 메서드
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
