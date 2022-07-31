package com.study.boardsystem.domain.type;

import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.domain.type
 * fileName       : Rank
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */

@Getter
public enum Rank {

    BASIC("새싹"),
    NORMAL("일반");

    private final String rankName;

    Rank(String rankName) {
        this.rankName = rankName;
    }
}
