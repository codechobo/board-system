package com.study.boardsystem.module.post.domain;

import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : PostRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new " +
            "com.study.boardsystem.web.dto.PostFindResponseDto(p.title, p.description) " +
            "from Post p " +
            "where p.userName =:userName")
    List<PostFindResponseDto> findByName(@Param("userName") String userName);
}
