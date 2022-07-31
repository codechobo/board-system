package com.study.boardsystem.module.post;

import com.study.boardsystem.module.post.domain.Post;
import com.study.boardsystem.module.post.web.dto.PostSaveRequestDto;
import com.study.boardsystem.module.user.domain.User;
import com.study.boardsystem.module.user.domain.type.Address;

/**
 * packageName    : com.study.boardsystem.module.post
 * fileName       : CreateDomain
 * author         : tkdwk567@naver.com
 * date           : 2022/07/22
 */

public abstract class CreateDomain {
    public User createUser(
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
        return user;
    }

    public Post createPost(String title, String description, User user) {
        Post post = PostSaveRequestDto.builder()
                .userNickname(user.getNickname())
                .title(title)
                .description(description)
                .build()
                .toEntity();

        post.addUser(user);
        return post;
    }

    public PostSaveRequestDto createPostSaveRequestDto(String title, String description) {
        return PostSaveRequestDto.builder()
                .title(title)
                .description(description)
                .build();
    }

}
