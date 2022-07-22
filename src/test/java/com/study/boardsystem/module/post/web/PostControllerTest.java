package com.study.boardsystem.module.post.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.module.post.CreateDomain;
import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.service.PostService;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.study.boardsystem.module.post.web
 * fileName       : PostControllerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/22
 */

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest extends CreateDomain {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("게시판 글 생성하기")
    void createPosts() throws Exception {
        User user = createUser("뚱이",
                "뚱이12341234",
                "뚱이@naver.com",
                "별가사리",
                "비키니시티",
                "어딘가",
                "어딘가살겠지");

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title("스폰지밥이 좋지?")
                .description("난 뚱이야 스폰지밥은 내 절친이야")
                .build();

        mockMvc.perform(post("/api/" + user.getId() + "/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postSaveRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value(user.getNickname()))
                .andExpect(jsonPath("$.title").value(postSaveRequestDto.getTitle()));
    }

    @Test
    @DisplayName("닉네임으로 게시글 찾기")
    void searchByName() throws Exception {
        User user = createUser("뚱이",
                "뚱이12341234",
                "뚱이@naver.com",
                "absabs",
                "비키니시티",
                "어딘가",
                "어딘가살겠지");
        userRepository.save(user);

        Post post = createPost("스폰지밥 재밌어", "인정인정이야 !!!", user);
        postRepository.save(post);

//        List<PostFindResponseDto> list = new ArrayList<>(List.of(PostFindResponseDto.builder()
//                .title(post.getTitle())
//                .description(post.getDescription())
//                .build()));
//        String json = objectMapper.writeValueAsString(list);


        mockMvc.perform(get("/api/posts/" + user.getNickname())
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk());


    }
}