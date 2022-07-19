package com.study.boardsystem.module.user.web.dto;

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

    private String city;

    private String address1;

    private String address2;

}
