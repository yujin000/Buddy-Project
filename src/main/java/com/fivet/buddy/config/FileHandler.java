package com.fivet.buddy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileHandler implements WebMvcConfigurer{
    @Value("${qna.root.path}")
    String qnaPath;

    @Value("${profile.root.path}")
    String profilePath;

    @Value("${notice.root.path}")
    String noticePath;

    @Value("${emoticon.root.path}")
    String emoticonPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/qnaImg/**").addResourceLocations(qnaPath);
        registry.addResourceHandler("/noticeImg/**").addResourceLocations(noticePath);
        registry.addResourceHandler("/member/selectProfileImg/**").addResourceLocations(profilePath);
        registry.addResourceHandler("/emoticon/**").addResourceLocations(emoticonPath);
    }
}
