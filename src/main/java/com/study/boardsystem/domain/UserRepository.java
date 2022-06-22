package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);

}
