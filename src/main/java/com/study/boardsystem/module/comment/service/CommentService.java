package com.study.boardsystem.module.comment.service;

import com.study.boardsystem.module.comment.domain.Comment;
import com.study.boardsystem.module.comment.domain.CommentRepository;
import com.study.boardsystem.module.comment.dto.CommentSaveRequestDto;
import com.study.boardsystem.module.comment.dto.CommentSaveResponseDto;
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

    public CommentSaveResponseDto saveComment(Long postId, CommentSaveRequestDto commentSaveRequestDto) {
        Comment comment = commentSaveRequestDto.toEntity();
//        comment.addPostsId(postId);

        Comment saveComment = commentRepository.save(comment);
        return CommentSaveResponseDto.toMapper(saveComment);
    }
}
