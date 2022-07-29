package com.study.boardsystem.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : SearchPostRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/29
 */
@Transactional(readOnly = true)
public interface SearchPostRepository extends PostRepository {

    List<Post> findByTitleContaining(String title);
}
