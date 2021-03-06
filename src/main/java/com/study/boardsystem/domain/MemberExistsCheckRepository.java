package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : MemberExistsCheckRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@Transactional(readOnly = true)
public interface MemberExistsCheckRepository extends JpaRepository<Member, Long> {
    boolean existsByName(String name);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
}
