package com.study.boardsystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.study.boardsystem.config
 * fileName       : WebConfig
 * author         : tkdwk567@naver.com
 * date           : 2022/06/22
 */

@Configuration
public class WebConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
