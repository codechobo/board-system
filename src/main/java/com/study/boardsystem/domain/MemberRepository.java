package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : MemberRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
}
