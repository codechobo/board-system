package com.study.boardsystem.module.user.web.dto;

import com.study.boardsystem.module.user.domain.type.Address;
import lombok.Data;

/**
 * packageName    : com.study.boardsystem.module.post.domain
 * fileName       : UserSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Data
public class UserSaveResponseDto {

    private String name;

    private String email;

    private String nickname;

    private Address address;
}
