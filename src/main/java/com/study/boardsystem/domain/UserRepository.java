package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String nickName);
}
