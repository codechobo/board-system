package com.study.boardsystem.module.post.service;

import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.domain.PostRepository;
import com.study.boardsystem.module.post.web.dto.PostFindResponseDto;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.post.web.dto.PostSaveResponseDto;
import com.study.boardsystem.module.post.web.dto.PostUpdateRequestDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.UserRepository;
import com.study.boardsystem.module.user.domain.type.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * packageName    : com.study.boardsystem.module.post.service
 * fileName       : PostServiceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = createUser(
                "이기영",
                "테스트1234",
                "기영@naver.com",
                "까까머리",
                "서울",
                "어딘가",
                "살겠지");

        Post post1 = createPost("검정고무신 재밌다", "기영이가 너무 귀여워 인정?!!", user);
        Post post2 = createPost("기철이형은 나빠!", "기철이형이 콜라를 안줘서 나빠요!", user);

        postRepository.save(post1);
        postRepository.save(post2);

    }

    @AfterEach
    public void afterEach() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판을 생성한다.")
    void createPost() {
        User user = createUser("스폰지밥",
                "테스트12341234",
                "스폰지밥@naver.com",
                "스폰지",
                "비키니시티",
                "어딘가",
                "살겠지");

        PostSaveRequestDto postSaveRequestDto = createPostSaveRequestDto(
                "스폰지밥 vs 뚱이",
                "스폰지밥하고 뚱이 중 누가 더 재밌을까용??");

        PostSaveResponseDto dto = postService.create(user.getId(), postSaveRequestDto);

        assertNotNull(dto);
        assertThat(dto.getTitle()).isEqualTo(postSaveRequestDto.getTitle());
    }


    @Test
    @DisplayName("게시판 조회한다.")
    void findPost() {
        // given
        User user = createUser("스폰지밥",
                "테스트12341234",
                "스폰지밥@naver.com",
                "스폰지",
                "비키니시티",
                "어딘가",
                "살겠지");

        PostSaveRequestDto postSaveRequestDto = createPostSaveRequestDto(
                "스폰지밥 vs 뚱이",
                "스폰지밥하고 뚱이 중 누가 더 재밌을까용??");

        PostSaveResponseDto dto = postService.create(user.getId(), postSaveRequestDto);

        // when
        List<PostFindResponseDto> result = postService.findByNamePosts(user.getNickname());

        // then
        assertThat(result).isNotNull();
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result)
                        .extracting("title")
                        .containsOnly(postSaveRequestDto.getTitle())
        );
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void findPostList() {
        List<Post> posts = postRepository.findAll();

        assertAll(
                () -> assertThat(posts).isNotNull(),
                () -> assertThat(posts)
                        .extracting("title")
                        .containsOnly("검정고무신 재밌다", "기철이형은 나빠!")
        );
    }

    @Test
    @DisplayName("게시글 삭제")
    void deletePost() {
        // given
        User user = createUser("뚱이",
                "뚱이12341234",
                "뚱이@naver.com",
                "별가사리",
                "비키니시티",
                "어딘가",
                "어딘가살겠지");

        Post post = createPost("뚱이는 귀엽다", "맞죠맞죠 뚱이 귀엽죠", user);
        postRepository.save(post);

        // when
        postService.deleteByIdPost(post.getId());

        // then
        assertThatThrownBy(() -> postRepository.findById(post.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다.")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 게시글입니다.");

    }

    @Test
    @DisplayName("게시글 업데이트")
    void updateByIdPost() {
        // given
        User user = createUser("뚱이",
                "뚱이12341234",
                "뚱이@naver.com",
                "별가사리",
                "비키니시티",
                "어딘가",
                "어딘가살겠지");

        Post updateBeforePost = createPost("뚱이는 귀엽다", "맞죠맞죠 뚱이 귀엽죠", user);
        String updateBeforeTitle = updateBeforePost.getTitle();
        String updateBeforeDescription = updateBeforePost.getDescription();
        postRepository.save(updateBeforePost);

        // when
        PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
                .title("뚱이는 뚱이다.")
                .description("아니아니 아니야")
                .build();
        postService.updateByIdPost(updateBeforePost.getId(), postUpdateRequestDto);

        // then
        Post updateAfterPost = postRepository.findById(updateBeforePost.getId()).orElseThrow();
        assertNotNull(updateAfterPost);
        assertThat(updateAfterPost.getTitle()).isEqualTo(updateBeforePost.getTitle());
        assertThat(updateAfterPost.getDescription()).isEqualTo(updateBeforePost.getDescription());
    }

    private User createUser(
            String name, String password, String email,
            String nickName, String city, String street, String zipcode) {
        User user = User.builder()
                .name(name)
                .password(password)
                .email(email)
                .nickname(nickName)
                .address(Address.builder()
                        .city(city)
                        .street(street)
                        .zipcode(zipcode)
                        .build())
                .build();
        userRepository.save(user);
        return user;
    }

    private Post createPost(String title, String description, User user) {
        Post post = PostSaveRequestDto.builder()
                .title(title)
                .description(description)
                .build()
                .toEntity();
        post.addUser(user);
        return post;
    }

    private PostSaveRequestDto createPostSaveRequestDto(String title, String description) {
        return PostSaveRequestDto.builder()
                .title(title)
                .description(description)
                .build();
    }

}