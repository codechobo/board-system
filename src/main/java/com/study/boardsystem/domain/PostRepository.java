package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : PostRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

public interface PostRepository extends JpaRepository<Post, Long> {
}
