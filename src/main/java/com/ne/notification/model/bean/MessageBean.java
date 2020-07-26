package com.ne.notification.model.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageBean extends BaseBean{

    String phoneNumber;
    @NotBlank
    String message;
    String firstName;
    String lastName;
    Long telegramBotChatId;
    String telegramBotUsername;
    String priority;
    String telegramChannelType;
}
