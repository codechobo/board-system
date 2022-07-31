package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : MemberRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByEmail(String email);
}
