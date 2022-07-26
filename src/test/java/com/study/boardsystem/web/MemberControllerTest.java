package com.study.boardsystem.web;

import com.study.boardsystem.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : MemberControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

@WebMvcTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("d")
    void MemberControllerTest() {

    }

}