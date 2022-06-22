package com.study.boardsystem.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : UserSaveResponseDto
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Data
public class UserSaveResponseDto {

    private String name;

    private String nickName;

    private String city;

    private String address1;

    private String address2;

}
