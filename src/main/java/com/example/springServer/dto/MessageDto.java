package com.example.springServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MessageDto {
    private Integer id;
    private String message;
    private LocalDate date;
    private UserInfoDto userInfo;
    private Integer chatId;
}
