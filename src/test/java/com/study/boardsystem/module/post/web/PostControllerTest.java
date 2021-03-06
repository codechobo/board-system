package com.study.boardsystem.module.post.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.boardsystem.module.post.CreateDomain;
import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.service.PostService;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    WebApplicationContext webApplicationContext;

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("????????? ??? ????????????")
    void createPosts() throws Exception {
        User user = createUser("??????",
                "??????12341234",
                "??????@naver.com",
                "????????????",
                "???????????????",
                "?????????",
                "??????????????????");

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title("??????????????? ???????")
                .description("??? ????????? ??????????????? ??? ????????????")
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
    @DisplayName("??????????????? ????????? ??????")
    void searchByName() throws Exception {
        // given
        User user = createUser();
        Post post = createPost(user);

        // when && then
        String content = objectMapper.writeValueAsString(List.of(PostFindResponseDto.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .build()));
        mockMvc.perform(get("/api/posts")
                        .param("nickname", user.getNickname()))
                .andDo(print())
                .andExpect(content().json(content));
    }

    @Test
    @DisplayName("?????? ?????????????????????")
    void list() throws Exception {
        User user = createUser();
        Post post1 = createPost(user);
        Post post2 = createPost(user);
        Post post3 = createPost(user);

        List<PostSaveResponseDto> list = new ArrayList<>() {
            {
                add(PostSaveResponseDto.builder()
                        .post(post1)
                        .build());

                add(PostSaveResponseDto.builder()
                        .post(post2)
                        .build());

                add(PostSaveResponseDto.builder()
                        .post(post3)
                        .build());
            }
        };

        mockMvc.perform(get("/api/posts/list"))
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    @DisplayName("????????? ??????")
    void deletePost() throws Exception {
        User user = createUser();
        Post post1 = createPost(user);
        Post post2 = createPost(user);
        Post post3 = createPost(user);

        mockMvc.perform(delete(""))



    }

    private Post createPost(User user) {
        Post post = createPost("???????????? ?????????", "?????????????????? !!!", user);
        postRepository.save(post);
        return post;
    }

    private User createUser() {
        User user = createUser("??????",
                "??????12341234",
                "??????@naver.com",
                "????????????",
                "???????????????",
                "?????????",
                "??????????????????");
        userRepository.save(user);
        return user;
    }


}