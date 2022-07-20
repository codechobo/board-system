package com.study.boardsystem.module.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.study.boardsystem.module.comment.domain
 * fileName       : CommentRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
