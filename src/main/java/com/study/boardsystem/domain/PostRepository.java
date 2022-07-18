package com.study.boardsystem.domain;

import com.study.boardsystem.web.dto.PostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : PostRepository
 * author         : tkdwk567@naver.com
 * date           : 2022/06/28
 */

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p.title as title, p.description as description " +
            "from Post p " +
            "where p.userName =:userName")
    List<PostView> findByName(@Param("userName") String userName);
}
