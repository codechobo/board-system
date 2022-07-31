package com.study.boardsystem.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : SearchPostRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/07/29
 */
@Transactional(readOnly = true)
public interface SearchPostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p " +
            "join fetch p.member " +
            "where p.id =:id")
    Optional<Post> findById(@Param("id") Long id);

    List<Post> findByTitleContaining(String title);
}
