package com.ne.notification.model.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TelegramBotBean {
    Long chatId;
    @NotBlank
    String message;
}
