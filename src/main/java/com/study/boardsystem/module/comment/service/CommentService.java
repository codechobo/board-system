package com.study.boardsystem.module.comment.service;

import com.study.boardsystem.module.comment.domain.Comment;
import com.study.boardsystem.module.comment.domain.CommentRepository;
import com.study.boardsystem.module.comment.web.dto.CommentSaveRequestDto;
import com.study.boardsystem.module.comment.web.dto.CommentSaveResponseDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.study.boardsystem.module.comment.service
 * fileName       : CommentService
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentSaveResponseDto create(CommentSaveRequestDto commentSaveRequestDto) {
        User user = userRepository.findByNickname(commentSaveRequestDto.getUserNickname())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 닉네임입니다."));

        Comment comment = Comment.builder()
                .userNickname(user.getNickname())
                .content(commentSaveRequestDto.getContent())
                .build();

        commentRepository.save(comment);
        return CommentSaveResponseDto.toMapper(comment);
    }
}
