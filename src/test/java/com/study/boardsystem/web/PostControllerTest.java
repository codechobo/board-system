package com.study.boardsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.service.MemberService;
import com.study.boardsystem.service.PostService;
import com.study.boardsystem.web.dto.member.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.post.PostSaveRequestDto;
import com.study.boardsystem.web.dto.post.PostSaveResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.study.boardsystem.web
 * fileName       : PostControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/28
 */

@WebMvcTest(controllers = PostController.class)
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MemberService mock = mock(MemberService.class);
        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .name("이기영")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build())
                .build();

        mock.joinMember(memberSaveRequestDto);
    }

    @Test
    @DisplayName("Post 생성한다")
    void createPost() throws Exception {
        // given
        String title = "기철이형 나쁘다";
        String description = "라면 혼자 먹고 진짜 나빠";

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title(title)
                .description(description)
                .build();

        MemberSaveRequestDto memberSaveRequestDto = MemberSaveRequestDto.builder()
                .name("이기영")
                .nickname("까까머리")
                .email("기영@naver.com")
                .password("test1234")
                .address(Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build())
                .build();
        Member member = memberSaveRequestDto.toEntity();
        member.setId(1L);

        Post post = Post.createPost(title, description, member);

        // when
        when(postService.savePost(anyLong(), any(PostSaveRequestDto.class)))
                .thenReturn(PostSaveResponseDto.toMapper(post, member.getNickname()));

        // then
        mockMvc.perform(post("/api/v1/" + member.getId() + "/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postSaveRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description));

        verify(postService).savePost(anyLong(), any(PostSaveRequestDto.class));
    }
}