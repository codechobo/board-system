package com.study.boardsystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.domain.Member;
import com.study.boardsystem.domain.Post;
import com.study.boardsystem.domain.PostRepository;
import com.study.boardsystem.domain.type.Address;
import com.study.boardsystem.service.PostService;
import com.study.boardsystem.web.dto.member_dto.MemberSaveRequestDto;
import com.study.boardsystem.web.dto.post_dto.PostSaveRequestDto;
import com.study.boardsystem.web.dto.post_dto.PostSaveResponseDto;
import com.study.boardsystem.web.dto.post_dto.PostSearchNameResponseDto;
import com.study.boardsystem.web.dto.post_dto.PostUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Post 생성한다")
    void createPost() throws Exception {
        // given
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

        String title = "기철이형 나쁘다";
        String description = "라면 혼자 먹고 진짜 나빠";

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .nickname(member.getNickname())
                .title(title)
                .description(description)
                .build();

        Post post = Post.createPost(title, description, member);

        // when
        when(postService.savePost(any(PostSaveRequestDto.class)))
                .thenReturn(PostSaveResponseDto.toMapper(post, member.getNickname()));

        // then
        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postSaveRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description));

        verify(postService).savePost(any(PostSaveRequestDto.class));
    }

    @Test
    @DisplayName("Post 조회한다")
    void getPost() throws Exception {
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .nickname("까까머리")
                .title("제목")
                .description("게시판 내용")
                .build();

        PostSaveResponseDto postSaveResponseDto = PostSaveResponseDto.toMapper(
                postSaveRequestDto.toEntity(),
                postSaveRequestDto.getNickname());

        when(postService.findByIdPost(anyLong())).thenReturn(postSaveResponseDto);

        mockMvc.perform(get("/api/v1/posts/" + 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper
                        .writeValueAsString(postSaveResponseDto)));

        verify(postService).findByIdPost(1L);
    }

    @Test
    @DisplayName("Post 삭제한다")
    void deletePost() throws Exception {
        doNothing().when(postService).removePost(anyLong());

        mockMvc.perform(delete("/api/v1/posts/" + 1L))
                .andDo(print())
                .andExpect(status().isOk());

        verify(postService).removePost(1L);
    }

    @Test
    @DisplayName("Post 검색한다.")
    void searchNamePost() throws Exception {
        Member member = new Member("이기철",
                "콜라도둑",
                "기철@naver.com",
                "test12341234",
                Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build());
        Post post1 = Post.createPost("원피스", "재미지지", member);
        Post post2 = Post.createPost("스폰지밥", "재미지지", member);
        Post post3 = Post.createPost("검정 고무신", "재미지지", member);

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        List<PostSearchNameResponseDto> list = posts.stream().map(post -> PostSearchNameResponseDto.builder()
                        .nickname(post.getMember().getNickname())
                        .title(post.getTitle())
                        .createDateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());


        given(postService.findByTitlePost(anyString()))
                .willReturn(list);

        String searchTitle = "";
        mockMvc.perform(get("/api/v1/posts")
                        .param("title", searchTitle))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));

        verify(postService).findByTitlePost(searchTitle);
    }

    @Test
    @DisplayName("Post 업데이트 한다")
    void updatePost() throws Exception {
        // given
        Member member = new Member("이기철",
                "콜라도둑",
                "기철@naver.com",
                "test12341234",
                Address.builder()
                        .city("서울")
                        .street("어딘가")
                        .zipcode("살겠지")
                        .build());

        willDoNothing().given(postService)
                .updateTitleAndDescription(anyLong(), any(PostUpdateRequestDto.class));

        PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
                .title("글 제목 업데이트")
                .description("내용 업데이트")
                .build();

        // when && then
        mockMvc.perform(put("/api/v1/posts/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postUpdateRequestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PostController.class));

        verify(postService).updateTitleAndDescription(anyLong(), any(PostUpdateRequestDto.class));
    }
}