package com.sparta.securityprac1.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String creater;
    private String contents;
    private String password;

    public PostRequestDto(String title, String creater, String contents, String password) {
        this.title = title;
        this.creater = creater;
        this.contents = contents;
        this.password = password;
    }
}
