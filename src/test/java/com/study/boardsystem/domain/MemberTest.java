package com.study.boardsystem.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.study.boardsystem.domain
 * fileName       : MemberTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */
class MemberTest {

    @Test
    @DisplayName("addWriterCount 메서드 테스트")
    void MemberTest() {
        // given
        Member member = Member.builder().nickname("까까").build();

        // when
        member.addWriteCount();

        // then
        int writeCount = member.getWriteCount();
        assertThat(writeCount).isEqualTo(1);
    }

}