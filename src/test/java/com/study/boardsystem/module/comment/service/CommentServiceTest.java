package com.study.boardsystem.module.comment.service;

import com.study.boardsystem.module.comment.domain.Comment;
import com.study.boardsystem.module.comment.domain.CommentRepository;
import com.study.boardsystem.module.comment.web.dto.CommentSaveRequestDto;
import com.study.boardsystem.module.user.domain.type.Address;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName    : com.study.boardsystem.module.comment.service
 * fileName       : CommentServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/21
 */

@SpringBootTest
class CommentServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("댓글 생성")
    void create_comment() {
        // given
        User user = createUser();
        CommentSaveRequestDto dto = createCommentSaveRequestDto(user);

        // when
        commentService.create(dto);

        // then
        Comment comment = commentRepository.findById(1L).get();
        assertNotNull(comment);
        assertThat(comment.getContent()).isEqualTo(dto.getContent());
    }

    private CommentSaveRequestDto createCommentSaveRequestDto(User user) {
        CommentSaveRequestDto dto = CommentSaveRequestDto.builder()
                .userNickname(user.getNickname())
                .content("뚱이가 너무 좋아요!")
                .build();
        return dto;
    }

    private User createUser() {
        User user = User.builder()
                .name("뚱이")
                .email("뚱이@naver.com")
                .nickname("불가사리")
                .password("test12341234")
                .address(Address.builder()
                        .city("바다")
                        .street("비키니시티")
                        .zipcode("바위")
                        .build())
                .build();
        userRepository.save(user);
        return user;
    }

}